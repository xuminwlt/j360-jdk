package me.j360.application.spring.test.runner;

import me.j360.application.spring.ec.EventCenterFactoryBean;
import me.j360.application.spring.runner.RunnerFactoryBean;
import me.j360.jdk.application.core.common.support.Config;
import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.ec.EventInfo;
import me.j360.jdk.application.ec.EventSubscriber;
import me.j360.jdk.application.ec.Observer;
import me.j360.jdk.application.ec.injvm.InjvmEventCenter;
import me.j360.jdk.application.ec.injvm.InjvmEventCenterFactory;
import me.j360.jdk.application.runner.DefaultRunnerFactory;
import me.j360.jdk.application.runner.JobRunner;
import me.j360.jdk.application.runner.RunnerPool;
import me.j360.jdk.application.runner.domain.TaskApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with j360-jdk -> me.j360.application.spring.test.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 16:55
 * 说明：
 */

@Configuration
public class RunnerSpringConfig implements ApplicationContextAware {
    private final Logger LOGGER = LoggerFactory.getLogger(RunnerSpringConfig.class);
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean(name="jobRunner")
    public JobRunner getJobRunner() throws Exception {
        RunnerFactoryBean runnerFactoryBean = new RunnerFactoryBean();
        runnerFactoryBean.setTaskApplication(getApplication());
        runnerFactoryBean.setJobRunner(new DefaultRunnerFactory(getApplication()).newRunner());
        return runnerFactoryBean.getObject();
    }

    @Bean(name="taskApplication")
    public TaskApplication getApplication(){
        TaskApplication application = new TaskApplication();
        application.setJobRunnerClass(TestJobRunner.class);

        Config config = new Config();
        config.setWorkThreads(3);
        config.setIdentity("node-001");
        application.setConfig(config);

        //绑定事件
        InjvmEventCenterFactory injvmEventCenterFactory = new InjvmEventCenterFactory();
        EventCenter eventCenter = injvmEventCenterFactory.getEventCenter(config);
        application.setEventCenter(eventCenter);

        return application;
    }

    @Bean(name="runnerPool")
    public RunnerPool getRunnerPool(){
        RunnerPool runnerPool = new RunnerPool(getApplication());
        return runnerPool;
    }


}
