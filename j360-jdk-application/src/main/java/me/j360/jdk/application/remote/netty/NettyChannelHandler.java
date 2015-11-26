package me.j360.jdk.application.remote.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import me.j360.jdk.application.remote.ChannelHandler;
import me.j360.jdk.application.remote.ChannelHandlerListener;
import me.j360.jdk.application.remote.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Robert HG (254963746@qq.com) on 11/3/15.
 */
public class NettyChannelHandler implements ChannelHandler {
    protected static final Logger LOGGER = LoggerFactory.getLogger(NettyChannelHandler.class);

    private ChannelFuture channelFuture;

    public NettyChannelHandler(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    @Override
    public ChannelHandler addListener(final ChannelHandlerListener listener) {

        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(final ChannelFuture future) throws Exception {
                listener.operationComplete(new Future() {
                    @Override
                    public boolean isSuccess() {
                        LOGGER.debug("isSuccess");

                        return future.isSuccess();
                    }

                    @Override
                    public Throwable cause() {
                        LOGGER.error("Throwable cause");
                        return future.cause();
                    }
                });
            }
        });

        return this;
    }
}
