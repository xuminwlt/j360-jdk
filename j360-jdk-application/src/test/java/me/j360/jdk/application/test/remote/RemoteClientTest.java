package me.j360.jdk.application.test.remote;

import me.j360.jdk.application.core.common.support.Application;
import me.j360.jdk.application.core.common.support.Config;
import me.j360.jdk.application.core.common.support.Job;
import me.j360.jdk.application.core.common.util.StringUtils;
import me.j360.jdk.application.remote.RemotingClient;
import me.j360.jdk.application.remote.RemotingClientConfig;
import me.j360.jdk.application.remote.exception.RemotingException;
import me.j360.jdk.application.remote.job.JobSubmitRequest;
import me.j360.jdk.application.remote.job.NodeType;
import me.j360.jdk.application.remote.netty.NettyRemotingClient;
import me.j360.jdk.application.remote.netty.NettyRemotingServer;
import me.j360.jdk.application.remote.protocol.CommandBodyWrapper;
import me.j360.jdk.application.remote.protocol.JobProtos;
import me.j360.jdk.application.remote.protocol.RemotingCommand;
import me.j360.jdk.application.runner.domain.Response;

import java.util.Collections;
import java.util.List;

/**
 * Created with j360-jdk -> me.j360.jdk.application.test.remote.
 * User: min_xu
 * Date: 2015/11/26
 * Time: 16:53
 * 说明：rpc 客户端
 */
public class RemoteClientTest {
    public static void main(String[] args) throws RemotingException {



        //RemotingClientDelegate remotingClient = new RemotingClientDelegate(remotingTransporter.getRemotingClient(application.getConfig(), new RemotingClientConfig()), application);

        RemotingClientConfig remoteClientConfig = new RemotingClientConfig();
        RemotingClient remotingClient = new NettyRemotingClient(remoteClientConfig);

        JobApplication application = new JobApplication();

        //封装job
        Job job = new Job();
        job.setTaskId(StringUtils.generateUUID());
        job.setTaskTrackerNodeGroup("test_trade_TaskTracker_0");
        job.setParam("shopId", "111");
        job.setNeedFeedback(false);


        Config config = new Config();
        config.setNodeType(NodeType.JOB_CLIENT);
        config.setNodeGroup("test_jobClient");
        config.setClusterName("test_cluster");
        config.setIdentity(StringUtils.generateUUID());
        config.setInvokeTimeoutMillis(60000);

        final List<Job> jobs = Collections.singletonList(job);

        application.setCommandBodyWrapper(new CommandBodyWrapper(config));

        remotingClient.start();

        try {
            JobSubmitRequest jobSubmitRequest = CommandBodyWrapper.wrapper(application, new JobSubmitRequest());
            jobSubmitRequest.setJobs(jobs);

            RemotingCommand requestCommand = RemotingCommand.createRequestCommand(
                    JobProtos.RequestCode.SUBMIT_JOB.code(), jobSubmitRequest);

            RemotingCommand response = remotingClient.invokeSync("192.168.247.1:35001",
                    requestCommand, application.getConfig().getInvokeTimeoutMillis());


        }catch (Exception e){

        }


        //模拟JobTracker注册到RemoteClient里面
        /*
        Node jobTracker = new Node();
        jobTracker.setIp("192.168.247.1");
        jobTracker.setPort(35001);
        jobTracker.setAvailable(true);
        jobTracker.setClusterName("test_cluster");
        jobTracker.setGroup("lts");
        jobTracker.setNodeType(NodeType.JOB_TRACKER);
        jobTracker.setIdentity("80EA21AF5DDE4293AAE9853779B80837");

        remotingClient.addJobTracker(jobTracker);*/

    }


    static class JobApplication extends Application{

    }
}
