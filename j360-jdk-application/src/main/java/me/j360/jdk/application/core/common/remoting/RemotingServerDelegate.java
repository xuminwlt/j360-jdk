package me.j360.jdk.application.core.common.remoting;

import me.j360.jdk.application.core.common.support.Application;
import me.j360.jdk.application.remote.AsyncCallback;
import me.j360.jdk.application.remote.Channel;
import me.j360.jdk.application.remote.RemotingProcessor;
import me.j360.jdk.application.remote.RemotingServer;
import me.j360.jdk.application.remote.exception.RemotingException;
import me.j360.jdk.application.remote.exception.RemotingSendException;
import me.j360.jdk.application.remote.protocol.RemotingCommand;

import java.util.concurrent.ExecutorService;

/**
 * @author Robert HG (254963746@qq.com) on 8/18/14.
 *         �� remotingServer �İ�װ
 */
public class RemotingServerDelegate {

    private RemotingServer remotingServer;
    private Application application;

    public RemotingServerDelegate(RemotingServer remotingServer, Application application) {
        this.remotingServer = remotingServer;
        this.application = application;
    }

    public void start() {
        try {
            remotingServer.start();
        } catch (RemotingException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerProcessor(int requestCode, RemotingProcessor processor,
                                  ExecutorService executor) {
        remotingServer.registerProcessor(requestCode, processor, executor);
    }

    public void registerDefaultProcessor(RemotingProcessor processor, ExecutorService executor) {
        remotingServer.registerDefaultProcessor(processor, executor);
    }

    public RemotingCommand invokeSync(Channel channel, RemotingCommand request)
            throws RemotingSendException {
        try {

            return remotingServer.invokeSync(channel, request,
                    application.getConfig().getInvokeTimeoutMillis());
        } catch (Throwable t) {
            throw new RemotingSendException(t);
        }
    }

    public void invokeAsync(Channel channel, RemotingCommand request, AsyncCallback asyncCallback)
            throws RemotingSendException {
        try {

            remotingServer.invokeAsync(channel, request,
                    application.getConfig().getInvokeTimeoutMillis(), asyncCallback);
        } catch (Throwable t) {
            throw new RemotingSendException(t);
        }
    }

    public void invokeOneway(Channel channel, RemotingCommand request)
            throws RemotingSendException {
        try {

            remotingServer.invokeOneway(channel, request,
                    application.getConfig().getInvokeTimeoutMillis());
        } catch (Throwable t) {
            throw new RemotingSendException(t);
        }
    }


    public void shutdown() {
        remotingServer.shutdown();
    }
}
