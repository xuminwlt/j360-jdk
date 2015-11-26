package me.j360.jdk.application.remote;


import me.j360.jdk.application.remote.exception.RemotingCommandException;
import me.j360.jdk.application.remote.protocol.RemotingCommand;

/**
 * 接收请求处理器，服务器与客户端通用
 */
public interface RemotingProcessor {
    public RemotingCommand processRequest(Channel channel, RemotingCommand request)
            throws RemotingCommandException;
}
