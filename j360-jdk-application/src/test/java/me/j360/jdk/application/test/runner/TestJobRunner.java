package me.j360.jdk.application.test.runner;


import me.j360.jdk.application.core.common.support.Job;
import me.j360.jdk.application.runner.Action;
import me.j360.jdk.application.runner.JobRunner;
import me.j360.jdk.application.runner.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJobRunner implements JobRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestJobRunner.class);

    @Override
    public Result run(Job job) throws Throwable {
        try {
//            Thread.sleep(1000L);
//
//            if (job.getRetryTimes() > 5) {
//                return new Result(Action.EXECUTE_FAILED, "重试次数超过5次了，放过你吧!");
//            }
//
//            if (SystemClock.now() % 2 == 1) {
//                return new Result(Action.EXECUTE_LATER, "稍后执行");
//            }

            // TODO 业务逻辑
            LOGGER.info("我要执行：" + job);
            // 会发送到 LTS (JobTracker上)
            //bizLogger.info("测试，业务日志啊啊啊啊啊");

        } catch (Exception e) {
            LOGGER.info("Run job failed!", e);
            return new Result(Action.EXECUTE_FAILED, e.getMessage());
        }
        return new Result(Action.EXECUTE_SUCCESS, "执行成功了，哈哈");
    }
}
