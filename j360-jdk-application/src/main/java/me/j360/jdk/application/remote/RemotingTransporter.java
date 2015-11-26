package me.j360.jdk.application.remote;

import me.j360.jdk.application.core.common.support.Config;

/**
 * @author Robert HG (254963746@qq.com) on 11/6/15.
 */
public interface RemotingTransporter {

    RemotingServer getRemotingServer(Config config, RemotingServerConfig remotingServerConfig);

    RemotingClient getRemotingClient(Config config, RemotingClientConfig remotingClientConfig);

}
