package me.j360.jdk.application.test.runner;

import me.j360.jdk.application.core.common.support.Config;
import me.j360.jdk.application.core.common.support.Job;
import me.j360.jdk.application.core.common.support.JobWrapper;
import me.j360.jdk.application.ec.*;
import me.j360.jdk.application.ec.injvm.InjvmEventCenterFactory;
import me.j360.jdk.application.runner.DefaultRunnerFactory;
import me.j360.jdk.application.runner.RunnerCallback;
import me.j360.jdk.application.runner.RunnerPool;
import me.j360.jdk.application.runner.domain.Response;
import me.j360.jdk.application.runner.domain.TaskApplication;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with j360-jdk -> me.j360.jdk.application.test.runner.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 14:44
 * 说明：
 */
public class RunnerTest {

    private AtomicInteger c = new AtomicInteger();

    @Test
    public void runnerTester() throws InterruptedException {
        Config config = new Config();
        config.setWorkThreads(2);
        config.setIdentity("node-001");
        TaskApplication application = new TaskApplication();
        application.setConfig(config);
        application.setJobRunnerClass(new TestJobRunner().getClass());
        application.setRunnerFactory(new DefaultRunnerFactory(application));

        //绑定事件
        InjvmEventCenterFactory injvmEventCenterFactory = new InjvmEventCenterFactory();
        EventCenter eventCenter = injvmEventCenterFactory.getEventCenter(config);
        application.setEventCenter(eventCenter);
        EventSubscriber eventSubscriber = new EventSubscriber("1", new Observer() {
            @Override
            public void onObserved(EventInfo eventInfo) {
                System.out.println(eventInfo.getTopic());
            }
        });
        eventCenter.subscribe(eventSubscriber,"topic-job");

        //绑定线程池
        application.setRunnerPool(new RunnerPool(application));
        //初始化好pool

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
        application.getRunnerPool().execute(jobWrapper,jobRunnerCallback);
        //拉去runner中的队列
        boolean running = application.getRunnerPool().getRunningJobManager().running("job-001");
        System.out.println(running);

        TimeUnit.SECONDS.sleep(1);
        application.getRunnerPool().execute(jobWrapper,jobRunnerCallback);
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

            System.out.println(c.incrementAndGet());
            return returnResponse.getJobWrapper();
        }
    }
}
