package me.j360.jdk.application.core.common.support;

import java.io.Serializable;

/**
 *
 */
public class JobWrapper implements Serializable{

    private String jobId;

    private Job job;

    public JobWrapper(String jobId, Job job) {
        this.jobId = jobId;
        this.job = job;
    }

    public JobWrapper() {
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "JobWrapper{" +
                "jobId='" + jobId + '\'' +
                ", job=" + job +
                '}';
    }
}
