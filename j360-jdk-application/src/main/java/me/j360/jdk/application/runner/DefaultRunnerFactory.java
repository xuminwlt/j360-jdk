package me.j360.jdk.application.runner;

import me.j360.jdk.application.runner.domain.TaskApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRunnerFactory implements RunnerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunnerFactory.class);
    private TaskApplication application;

    public DefaultRunnerFactory(TaskApplication application) {
        this.application = application;
    }

    public JobRunner newRunner() {
        try {
            return (JobRunner) application.getJobRunnerClass().newInstance();
        } catch (InstantiationException e) {
            LOGGER.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
