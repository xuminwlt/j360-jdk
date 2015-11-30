package me.j360.jdk.application.core.common.support;

import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.remote.protocol.CommandBodyWrapper;

/**
 * Created with j360-jdk -> me.j360.jdk.application.core.common.support.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 14:28
 * 说明：
 */
public abstract class Application {

    // 节点配置信息
    private Config config;


    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public EventCenter getEventCenter() {
        return eventCenter;
    }

    public void setEventCenter(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    // 事件中心
    private EventCenter eventCenter;


    private CommandBodyWrapper commandBodyWrapper;

    public CommandBodyWrapper getCommandBodyWrapper() {
        return commandBodyWrapper;
    }

    public void setCommandBodyWrapper(CommandBodyWrapper commandBodyWrapper) {
        this.commandBodyWrapper = commandBodyWrapper;
    }

}
