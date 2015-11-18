package me.j360.application.spring.runner;

import me.j360.jdk.application.core.common.support.Application;
import me.j360.jdk.application.logger.BizLogger;
import me.j360.jdk.application.runner.JobRunner;
import me.j360.jdk.application.runner.domain.TaskApplication;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created with j360-jdk -> me.j360.application.spring.runner.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 14:52
 * 说明：
 */
public class RunnerFactoryBean  implements FactoryBean<JobRunner>,
        InitializingBean, DisposableBean {

    public TaskApplication getTaskApplication() {
        return taskApplication;
    }

    public void setTaskApplication(TaskApplication taskApplication) {
        this.taskApplication = taskApplication;
    }

    private TaskApplication taskApplication;

    public JobRunner getJobRunner() {
        return jobRunner;
    }

    public void setJobRunner(JobRunner jobRunner) {
        this.jobRunner = jobRunner;
    }

    private JobRunner jobRunner;

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public JobRunner getObject() throws Exception {
        return jobRunner;
    }

    @Override
    public Class<?> getObjectType() {
        return jobRunner.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}