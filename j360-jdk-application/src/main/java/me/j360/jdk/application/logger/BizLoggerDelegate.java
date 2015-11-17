package me.j360.jdk.application.logger;

import me.j360.jdk.application.common.constant.Constants;
import me.j360.jdk.application.common.support.Config;
import me.j360.jdk.application.common.util.CollectionUtils;
import me.j360.jdk.application.common.util.NamedThreadFactory;
import me.j360.jdk.application.logger.console.ConsoleBizLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created with j360-jdk -> me.j360.jdk.application.logger.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 15:43
 * 说明：
 */
public class BizLoggerDelegate implements BizLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger(BizLoggerDelegate.class);

    // 3S 检查输盘一次日志
    private int flushPeriod;

    private BizLogger jobLogger;
    private boolean lazyLog = false;
    private ScheduledExecutorService executor;
    private ScheduledFuture scheduledFuture;
    private BlockingQueue<BizLogPo> memoryQueue;
    // 日志批量刷盘数量
    private int batchFlushSize = 100;
    private int overflowSize = 10000;
    // 内存中最大的日志量阀值
    private int maxMemoryLogSize;
    private AtomicBoolean flushing = new AtomicBoolean(false);

    public BizLoggerDelegate(Config config) {
        BizLoggerFactory jobLoggerFactory = new BizLoggerFactory() {
            @Override
            public BizLogger getJobLogger() {
                return new ConsoleBizLogger();
            }
        };
        jobLogger = jobLoggerFactory.getJobLogger();
        lazyLog = config.getParameter(Constants.LAZY_JOB_LOGGER, false);
        if (lazyLog) {

            // 无界Queue
            memoryQueue = new LinkedBlockingQueue<BizLogPo>();
            maxMemoryLogSize = config.getParameter(Constants.LAZY_JOB_LOGGER_MEM_SIZE, 1000);
            flushPeriod = config.getParameter(Constants.LAZY_JOB_LOGGER_CHECK_PERIOD, 3);

            executor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("LazyJobLogger"));
            scheduledFuture = executor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (flushing.compareAndSet(false, true)) {
                            checkAndFlush();
                        }
                    } catch (Throwable t) {
                        LOGGER.error("CheckAndFlush log error", t);
                    }
                }
            }, flushPeriod, flushPeriod, TimeUnit.SECONDS);

        }
    }

    /**
     * 检查内存中是否有日志,如果有就批量刷盘
     */
    private void checkAndFlush() {
        try {
            int nowSize = memoryQueue.size();
            if (nowSize == 0) {
                return;
            }
            List<BizLogPo> batch = new ArrayList<BizLogPo>();
            for (int i = 0; i < nowSize; i++) {
                BizLogPo jobLogPo = memoryQueue.poll();
                batch.add(jobLogPo);

                if (batch.size() >= batchFlushSize) {
                    flush(batch);
                }
            }
            if (batch.size() > 0) {
                flush(batch);
            }

        } finally {
            flushing.compareAndSet(true, false);
        }
    }

    private void checkOverflowSize() {
        if (memoryQueue.size() > overflowSize) {
            throw new BizLogException("Memory Log size is " + memoryQueue.size() + " , please check the JobLogger is available");
        }
    }

    private void flush(List<BizLogPo> batch) {
        boolean flushSuccess = false;
        try {
            jobLogger.log(batch);
            flushSuccess = true;
        } finally {
            if (!flushSuccess) {
                memoryQueue.addAll(batch);
            }
            batch.clear();
        }
    }

    /**
     * 检查内存中的日志量是否超过阀值,如果超过需要批量刷盘日志
     */
    private void checkCapacity() {
        if (memoryQueue.size() > maxMemoryLogSize) {
            // 超过阀值,需要批量刷盘
            if (flushing.compareAndSet(false, true)) {
                // 这里可以采用new Thread, 因为这里只会同时new一个
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            checkAndFlush();
                        } catch (Throwable t) {
                            LOGGER.error("Capacity full flush error", t);
                        }
                    }
                }).start();
            }
        }
    }

    @Override
    public void log(BizLogPo jobLogPo) {
        if (jobLogPo == null) {
            return;
        }
        if (lazyLog) {
            checkOverflowSize();
            memoryQueue.offer(jobLogPo);
            checkCapacity();
        } else {
            jobLogger.log(jobLogPo);
        }
    }

    @Override
    public void log(List<BizLogPo> jobLogPos) {
        if (CollectionUtils.isEmpty(jobLogPos)) {
            return;
        }
        if (lazyLog) {
            checkOverflowSize();
            for (BizLogPo jobLogPo : jobLogPos) {
                memoryQueue.offer(jobLogPo);
            }
            // checkCapacity
            checkCapacity();
        } else {
            jobLogger.log(jobLogPos);
        }
    }

}
