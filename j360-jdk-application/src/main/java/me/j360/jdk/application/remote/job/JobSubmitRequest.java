package me.j360.jdk.application.remote.job;

import me.j360.jdk.application.core.common.support.Job;
import me.j360.jdk.application.remote.annotation.NotNull;
import me.j360.jdk.application.remote.protocol.AbstractRemotingCommandBody;

import java.util.List;

/**
 * @author Robert HG (254963746@qq.com) on 7/24/14.
 *         ���񴫵���Ϣ
 */
public class JobSubmitRequest extends AbstractRemotingCommandBody {

    @NotNull
    private List<Job> jobs;

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

}
