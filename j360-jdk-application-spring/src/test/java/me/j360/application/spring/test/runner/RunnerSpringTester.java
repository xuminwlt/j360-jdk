package me.j360.application.spring.test.runner;

import me.j360.application.spring.test.ec.EventCenterSpringConfig;
import me.j360.jdk.application.core.common.constant.EcTopic;
import me.j360.jdk.application.core.common.support.Config;
import me.j360.jdk.application.core.common.support.Job;
import me.j360.jdk.application.core.common.support.JobWrapper;
import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.ec.EventInfo;
import me.j360.jdk.application.ec.EventSubscriber;
import me.j360.jdk.application.ec.Observer;
import me.j360.jdk.application.ec.injvm.InjvmEventCenterFactory;
import me.j360.jdk.application.runner.DefaultRunnerFactory;
import me.j360.jdk.application.runner.JobRunner;
import me.j360.jdk.application.runner.RunnerCallback;
import me.j360.jdk.application.runner.RunnerPool;
import me.j360.jdk.application.runner.domain.Response;
import me.j360.jdk.application.runner.domain.TaskApplication;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with j360-jdk -> me.j360.application.spring.test.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 17:01
 * 说明：
 */
public class RunnerSpringTester {
    private AtomicInteger c = new AtomicInteger();
    private final Logger LOGGER = LoggerFactory.getLogger(RunnerSpringTester.class);

    @Test
    public void runnerSpringTest() throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(RunnerSpringConfig.class);
        TaskApplication application = (TaskApplication) context.getBean("taskApplication");
        RunnerPool runnerPool = (RunnerPool) context.getBean("runnerPool");
        application.setRunnerPool(runnerPool);


        //开始执行--job--
        // 线程安全的
        JobRunnerCallback jobRunnerCallback = new JobRunnerCallback();
        final JobWrapper jobWrapper = new JobWrapper();
        Job job = new Job();
        job.setCronExpression("1,2,3");
        job.setParam("234","23");
        job.setRetryTimes(3);
        job.setTaskId("232323");
        jobWrapper.setJob(job);
        jobWrapper.setJobId("job-001");
        runnerPool.execute(jobWrapper, jobRunnerCallback);

        //拉去runner中的队列
        boolean running = runnerPool.getRunningJobManager().running("job-001");
        LOGGER.debug(String.valueOf(running));

        //发起通知
        application.getEventCenter().publishAsync(new EventInfo(EcTopic.WORK_THREAD_CHANGE));

        TimeUnit.SECONDS.sleep(1);
        runnerPool.execute(jobWrapper, jobRunnerCallback);
        TimeUnit.SECONDS.sleep(1);

        TimeUnit.SECONDS.sleep(1);
    }


    /**
     * 任务执行的回调(任务执行完之后线程回调这个函数)
     */
    private class JobRunnerCallback implements RunnerCallback {
        @Override
        public JobWrapper runComplete(Response response) {
            final Response returnResponse = new Response();

            LOGGER.debug(String.valueOf(c.incrementAndGet()));
            return returnResponse.getJobWrapper();
        }
    }
}
