package me.j360.jdk.application.runner;


import me.j360.jdk.application.core.common.support.JobWrapper;
import me.j360.jdk.application.runner.domain.Response;

public interface RunnerCallback {

    /**
     * 执行完成, 可能是成功, 也可能是失败
     * @param response
     * @return 如果有新的任务, 那么返回新的任务过来
     */
    public JobWrapper runComplete(Response response);

}
