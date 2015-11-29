package me.j360.jdk.application.remote.protocol;

import me.j360.jdk.application.core.common.support.Application;
import me.j360.jdk.application.core.common.support.Config;

public class CommandBodyWrapper {

    private Config config;

    public CommandBodyWrapper(Config config) {
        this.config = config;
    }

    public <T extends AbstractRemotingCommandBody> T wrapper(T commandBody) {
        commandBody.setNodeGroup(config.getNodeGroup());
        //commandBody.setNodeType(config.getNodeType().name());
        commandBody.setIdentity(config.getIdentity());
        return commandBody;
    }

    public static <T extends AbstractRemotingCommandBody> T wrapper(Application application, T commandBody) {
        return application.getCommandBodyWrapper().wrapper(commandBody);
    }

}
