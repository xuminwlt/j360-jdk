package me.j360.application.spring.ec;

import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.logger.BizLogger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created with j360-jdk -> me.j360.application.spring.ec.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:59
 * 说明：
 */
public class EventCenterFactoryBean implements FactoryBean<EventCenter>,
        InitializingBean, DisposableBean {


    public EventCenter getEventCenter() {
        return eventCenter;
    }

    public void setEventCenter(EventCenter eventCenter) {
        this.eventCenter = eventCenter;
    }

    private EventCenter eventCenter;

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public EventCenter getObject() throws Exception {
        return eventCenter;
    }

    @Override
    public Class<?> getObjectType() {
        return eventCenter.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}