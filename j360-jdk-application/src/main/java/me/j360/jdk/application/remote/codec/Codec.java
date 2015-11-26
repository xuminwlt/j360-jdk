package me.j360.jdk.application.remote.codec;



import me.j360.jdk.application.remote.protocol.RemotingCommand;

import java.nio.ByteBuffer;

public interface Codec {

    RemotingCommand decode(final ByteBuffer byteBuffer) throws Exception;

    ByteBuffer encode(final RemotingCommand remotingCommand) throws Exception;

}
