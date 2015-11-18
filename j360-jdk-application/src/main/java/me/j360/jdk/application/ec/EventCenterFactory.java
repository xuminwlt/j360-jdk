package me.j360.jdk.application.ec;

import me.j360.jdk.application.common.support.Config;

/**
 * Created with j360-jdk -> me.j360.jdk.application.ec.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:24
 * 说明：
 */
public interface EventCenterFactory {

    EventCenter getEventCenter(Config config);
}
