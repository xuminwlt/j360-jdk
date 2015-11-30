package me.j360.jdk.application.remote.codec;


import me.j360.jdk.application.remote.serialize.AdaptiveSerializable;
import me.j360.jdk.application.remote.serialize.FastJsonSerializable;
import me.j360.jdk.application.remote.serialize.RemotingSerializable;

public abstract class AbstractCodec implements Codec {

    protected RemotingSerializable getRemotingSerializable(int serializableTypeId) {

        /*RemotingSerializable serializable = null;
        if (serializableTypeId > 0) {
            serializable = AdaptiveSerializable.getSerializableById(serializableTypeId);
            if (serializable == null) {
                throw new IllegalArgumentException("Can not support RemotingSerializable that serializableTypeId=" + serializableTypeId);
            }
        } else {
            serializable = null;//ExtensionLoader.getExtensionLoader(RemotingSerializable.class).getAdaptiveExtension();
        }
        return serializable;*/
        return new FastJsonSerializable();
    }

}
