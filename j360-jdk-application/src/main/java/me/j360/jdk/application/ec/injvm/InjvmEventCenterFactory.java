package me.j360.jdk.application.ec.injvm;

import me.j360.jdk.application.core.common.support.Config;
import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.ec.EventCenterFactory;

/**
 * Created with j360-jdk -> me.j360.jdk.application.ec.injvm.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:27
 * 说明：
 */
public class InjvmEventCenterFactory implements EventCenterFactory {

    @Override
    public EventCenter getEventCenter(Config config) {
        return new InjvmEventCenter();
    }
}
