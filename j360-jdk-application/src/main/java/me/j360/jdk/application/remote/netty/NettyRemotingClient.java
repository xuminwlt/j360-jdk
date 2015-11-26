package me.j360.jdk.application.remote.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import me.j360.jdk.application.core.common.util.NamedThreadFactory;
import me.j360.jdk.application.remote.*;
import me.j360.jdk.application.remote.Channel;
import me.j360.jdk.application.remote.ChannelFuture;
import me.j360.jdk.application.remote.common.RemotingHelper;
import me.j360.jdk.application.remote.exception.RemotingException;
import me.j360.jdk.application.remote.protocol.RemotingCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;


/**
 * @author Robert HG (254963746@qq.com) on 11/3/15.
 */
public class NettyRemotingClient extends AbstractRemotingClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemotingHelper.RemotingLogName);

    private final Bootstrap bootstrap = new Bootstrap();
    private final EventLoopGroup eventLoopGroup;
    private DefaultEventExecutorGroup defaultEventExecutorGroup;

    public NettyRemotingClient(final RemotingClientConfig remotingClientConfig) {
        this(remotingClientConfig, null);
    }

    public NettyRemotingClient(final RemotingClientConfig remotingClientConfig,
                               final ChannelEventListener channelEventListener) {
        super(remotingClientConfig, channelEventListener);

        this.eventLoopGroup = new NioEventLoopGroup(remotingClientConfig.getClientSelectorThreads());
    }

    @Override
    protected void clientStart() throws RemotingException {

        //NettyLogger.setNettyLoggerFactory();

        this.defaultEventExecutorGroup = new DefaultEventExecutorGroup(
                remotingClientConfig.getClientWorkerThreads(),
                new NamedThreadFactory("NettyClientWorkerThread_")
        );

        final NettyCodecFactory nettyCodecFactory = new NettyCodecFactory(getCodec());

        this.bootstrap.group(this.eventLoopGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(//
                        defaultEventExecutorGroup, //
                        nettyCodecFactory.getEncoder(), //
                        nettyCodecFactory.getDecoder(), //
                        new IdleStateHandler(remotingClientConfig.getReaderIdleTimeSeconds(), remotingClientConfig.getWriterIdleTimeSeconds(), remotingClientConfig.getClientChannelMaxIdleTimeSeconds()),//
                        new NettyConnectManageHandler(), //
                        new NettyClientHandler());
            }
        });

    }

    @Override
    protected void clientShutdown() {

        this.eventLoopGroup.shutdownGracefully();

        if (this.defaultEventExecutorGroup != null) {
            this.defaultEventExecutorGroup.shutdownGracefully();
        }
    }

    @Override
    protected me.j360.jdk.application.remote.ChannelFuture connect(SocketAddress socketAddress) {
        io.netty.channel.ChannelFuture channelFuture = this.bootstrap.connect(socketAddress);
        return new me.j360.jdk.application.remote.netty.NettyChannelFuture(channelFuture);
    }

    class NettyClientHandler extends SimpleChannelInboundHandler<RemotingCommand> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, RemotingCommand msg) throws Exception {
            processMessageReceived(new NettyChannel(ctx), msg);
        }
    }

    class NettyConnectManageHandler extends ChannelDuplexHandler {
        @Override
        public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
                            SocketAddress localAddress, ChannelPromise promise) throws Exception {
            final String local = localAddress == null ? "UNKNOW" : localAddress.toString();
            final String remote = remoteAddress == null ? "UNKNOW" : remoteAddress.toString();
            LOGGER.info("CLIENT : CONNECT  {} => {}", local, remote);
            super.connect(ctx, remoteAddress, localAddress, promise);

            if (channelEventListener != null) {
                assert remoteAddress != null;
                putRemotingEvent(new RemotingEvent(RemotingEventType.CONNECT, remoteAddress
                        .toString(), new NettyChannel(ctx)));
            }
        }

        @Override
        public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {

            Channel channel = new NettyChannel(ctx);

            final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(channel);
            LOGGER.info("CLIENT : DISCONNECT {}", remoteAddress);
            closeChannel(channel);
            super.disconnect(ctx, promise);

            if (channelEventListener != null) {
                putRemotingEvent(new RemotingEvent(RemotingEventType.CLOSE, remoteAddress, channel));
            }
        }


        @Override
        public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
            Channel channel = new NettyChannel(ctx);

            final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(channel);
            LOGGER.info("CLIENT : CLOSE {}", remoteAddress);
            closeChannel(channel);
            super.close(ctx, promise);

            if (channelEventListener != null) {
                putRemotingEvent(new RemotingEvent(RemotingEventType.CLOSE, remoteAddress, channel));
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

            Channel channel = new NettyChannel(ctx);

            final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(channel);
            LOGGER.warn("CLIENT : exceptionCaught {}", remoteAddress);
            LOGGER.warn("CLIENT : exceptionCaught exception.", cause);
            closeChannel(channel);
            if (channelEventListener != null) {
                putRemotingEvent(new RemotingEvent(RemotingEventType.EXCEPTION, remoteAddress, channel));
            }
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;

                Channel channel = new NettyChannel(ctx);

                final String remoteAddress = RemotingHelper.parseChannelRemoteAddr(channel);

                if (event.state().equals(io.netty.handler.timeout.IdleState.ALL_IDLE)) {
                    LOGGER.warn("CLIENT : IDLE [{}]", remoteAddress);
                    closeChannel(channel);
                }

                if (channelEventListener != null) {
                    RemotingEventType remotingEventType = RemotingEventType.valueOf(event.state().name());
                    putRemotingEvent(new RemotingEvent(remotingEventType,
                            remoteAddress, channel));
                }
            }

            ctx.fireUserEventTriggered(evt);
        }
    }


}
