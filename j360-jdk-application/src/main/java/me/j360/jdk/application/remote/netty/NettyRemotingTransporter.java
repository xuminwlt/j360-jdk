package me.j360.jdk.application.remote.netty;

import me.j360.jdk.application.core.common.support.Config;
import me.j360.jdk.application.remote.*;

/**
 * @author Robert HG (254963746@qq.com) on 11/6/15.
 */
public class NettyRemotingTransporter implements RemotingTransporter {

    @Override
    public RemotingServer getRemotingServer(Config config, RemotingServerConfig remotingServerConfig) {
        return new NettyRemotingServer(remotingServerConfig);
    }

    @Override
    public RemotingClient getRemotingClient(Config config, RemotingClientConfig remotingClientConfig) {
        return new NettyRemotingClient(remotingClientConfig);
    }
}
