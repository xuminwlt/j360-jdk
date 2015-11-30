package me.j360.jdk.application.test.remote;

import me.j360.jdk.application.core.common.constant.Constants;
import me.j360.jdk.application.core.common.remoting.RemotingServerDelegate;
import me.j360.jdk.application.core.common.remoting.server.RemotingDispatcher;
import me.j360.jdk.application.core.common.support.Application;
import me.j360.jdk.application.core.common.support.Config;
import me.j360.jdk.application.core.common.util.NamedThreadFactory;
import me.j360.jdk.application.core.common.util.StringUtils;
import me.j360.jdk.application.remote.*;
import me.j360.jdk.application.remote.netty.NettyRemotingClient;
import me.j360.jdk.application.remote.netty.NettyRemotingServer;

import java.util.concurrent.Executors;

/**
 * Created with j360-jdk -> me.j360.jdk.application.test.remote.
 * User: min_xu
 * Date: 2015/11/26
 * Time: 16:53
 * 说明：rpc 服务器端，启动nettyServer作为remoteServer实现
 */
public class RemoteServerTest {
    public static void main(String[] args){
        RemotingTransporter remotingTransporter = new RemotingTransporter() {
            @Override
            public RemotingServer getRemotingServer(Config config, RemotingServerConfig remotingServerConfig) {
                return new NettyRemotingServer(remotingServerConfig);
            }

            @Override
            public RemotingClient getRemotingClient(Config config, RemotingClientConfig remotingClientConfig) {
                return new NettyRemotingClient(remotingClientConfig);
            }
        };

        Config config = new Config();
        config.setListenPort(35001);
        //config.setNodeType(NodeType.JOB_TRACKER);
        config.setNodeGroup("lts");
        config.setClusterName("test_cluster");
        config.setIdentity(StringUtils.generateUUID());
        config.setInvokeTimeoutMillis(60000);

        config.setParameter("job.queue", "mysql");
        config.setParameter("jdbc.url", "jdbc:mysql://127.0.0.1:3306/lts");
        config.setParameter("jdbc.username", "root");
        config.setParameter("jdbc.password", "root");

        RemotingServerConfig remotingServerConfig = new RemotingServerConfig();
        // config 配置
        if (config.getListenPort() == 0) {
            config.setListenPort(Constants.JOB_TRACKER_DEFAULT_LISTEN_PORT);
            //node.setPort(config.getListenPort());
        }
        remotingServerConfig.setListenPort(config.getListenPort());

        TaskApplication application = new TaskApplication();
        application.setConfig(config);


        RemotingServerDelegate remotingServer = new RemotingServerDelegate(remotingTransporter.getRemotingServer(config, remotingServerConfig), application);

        //JobTracker -> beforeStart();
        application.setRemotingServer(remotingServer);


        //remote start
        remotingServer.start();

        RemotingProcessor defaultProcessor = new RemotingDispatcher(application);
        if (defaultProcessor != null) {
            int processorSize = config.getParameter(Constants.PROCESSOR_THREAD, Constants.DEFAULT_PROCESSOR_THREAD);
            remotingServer.registerDefaultProcessor(defaultProcessor,
                    Executors.newFixedThreadPool(processorSize, new NamedThreadFactory(RemoteServerTest.class.getSimpleName())));
        }

    }

    static class TaskApplication extends Application{
        public RemotingServerDelegate getRemotingServer() {
            return remotingServer;
        }

        public void setRemotingServer(RemotingServerDelegate remotingServer) {
            this.remotingServer = remotingServer;
        }

        private RemotingServerDelegate remotingServer;
    }
}
