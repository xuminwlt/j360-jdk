package me.j360.application.spring.test.ec;

import me.j360.application.spring.ec.EventCenterFactoryBean;
import me.j360.application.spring.logger.BizLoggerFactoryBean;
import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.ec.injvm.InjvmEventCenter;
import me.j360.jdk.application.logger.BizLogger;
import me.j360.jdk.application.logger.console.ConsoleBizLogger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created with j360-jdk -> me.j360.application.spring.test.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 16:55
 * 说明：
 */

@Configuration
public class EventCenterSpringConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean(name="eventCenter")
    public EventCenter getEventCenter() throws Exception {
        EventCenterFactoryBean eventCenterFactoryBean = new EventCenterFactoryBean();
        eventCenterFactoryBean.setEventCenter(new InjvmEventCenter());
        return eventCenterFactoryBean.getObject();
    }

}
