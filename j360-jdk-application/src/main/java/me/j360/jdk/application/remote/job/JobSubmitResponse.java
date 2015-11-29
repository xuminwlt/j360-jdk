package me.j360.jdk.application.remote.job;


import me.j360.jdk.application.core.common.support.Job;
import me.j360.jdk.application.remote.protocol.AbstractRemotingCommandBody;

import java.util.List;

/**
 * @author Robert HG (254963746@qq.com) on 7/24/14.
 * ���񴫵���Ϣ
 */
public class JobSubmitResponse extends AbstractRemotingCommandBody {

    private Boolean success = true;

    private String msg;

    // ʧ�ܵ�jobs
    private List<Job> failedJobs;

    public List<Job> getFailedJobs() {
        return failedJobs;
    }

    public void setFailedJobs(List<Job> failedJobs) {
        this.failedJobs = failedJobs;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
