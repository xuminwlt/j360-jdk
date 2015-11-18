package me.j360.jdk.application.test.ec;

import me.j360.jdk.application.common.support.Config;
import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.ec.EventInfo;
import me.j360.jdk.application.ec.EventSubscriber;
import me.j360.jdk.application.ec.Observer;
import me.j360.jdk.application.ec.injvm.InjvmEventCenterFactory;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created with j360-jdk -> me.j360.jdk.application.test.ec.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:49
 * 说明：
 */
public class EventCenterTest {

    @Test
    public void ecTester() throws InterruptedException {
        Config config = new Config();
        InjvmEventCenterFactory injvmEventCenterFactory = new InjvmEventCenterFactory();
        EventCenter eventCenter = injvmEventCenterFactory.getEventCenter(config);

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
