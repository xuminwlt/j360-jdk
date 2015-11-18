package me.j360.jdk.application.runner;


import me.j360.jdk.application.core.common.support.Job;


public interface JobRunner {

    /**
     * 执行任务
     * 抛出异常则消费失败, 返回null则认为是消费成功
     */
    public Result run(Job job) throws Throwable;

}
