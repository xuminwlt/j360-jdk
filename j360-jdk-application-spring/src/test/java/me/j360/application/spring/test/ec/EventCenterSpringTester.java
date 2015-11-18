package me.j360.application.spring.test.ec;

import me.j360.application.spring.test.logger.LoggerSpringConfig;
import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.ec.EventInfo;
import me.j360.jdk.application.ec.EventSubscriber;
import me.j360.jdk.application.ec.Observer;
import me.j360.jdk.application.logger.BizLogPo;
import me.j360.jdk.application.logger.BizLogger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with j360-jdk -> me.j360.application.spring.test.
 * User: min_xu
 * Date: 2015/11/17
 * Time: 17:01
 * 说明：
 */
public class EventCenterSpringTester {

    @Test
    public void eventCenterSpringTest() throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(EventCenterSpringConfig.class);
        EventCenter eventCenter = (EventCenter) context.getBean("eventCenter");

        EventSubscriber eventSubscriber = new EventSubscriber("1", new Observer() {
            @Override
            public void onObserved(EventInfo eventInfo) {
                System.out.println(eventInfo.getTopic());
            }
        });
        eventCenter.subscribe(eventSubscriber,"topic-string");

        EventInfo eventInfo = new EventInfo("topic-string");
        eventCenter.publishSync(eventInfo);
        TimeUnit.SECONDS.sleep(1);
        eventCenter.publishAsync(eventInfo);
        TimeUnit.SECONDS.sleep(1);
    }



}
