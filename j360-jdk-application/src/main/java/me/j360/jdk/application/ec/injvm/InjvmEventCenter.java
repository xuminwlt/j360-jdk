package me.j360.jdk.application.ec.injvm;

import me.j360.jdk.application.core.common.constant.Constants;
import me.j360.jdk.application.core.common.support.ConcurrentHashSet;
import me.j360.jdk.application.core.common.util.JSONUtils;
import me.j360.jdk.application.ec.EventCenter;
import me.j360.jdk.application.ec.EventInfo;
import me.j360.jdk.application.ec.EventSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with j360-jdk -> me.j360.jdk.application.ec.injvm.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:27
 * 说明：
 */
public class InjvmEventCenter implements EventCenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventCenter.class.getName());

    private final ConcurrentHashMap<String, Set<EventSubscriber>> ecMap =
            new ConcurrentHashMap<String, Set<EventSubscriber>>();

    private final ExecutorService executor = Executors.newFixedThreadPool(Constants.AVAILABLE_PROCESSOR * 2);

    public void subscribe(EventSubscriber subscriber, String... topics) {
        for (String topic : topics) {
            Set<EventSubscriber> subscribers = ecMap.get(topic);
            if (subscribers == null) {
                subscribers = new ConcurrentHashSet<EventSubscriber>();
                Set<EventSubscriber> oldSubscribers = ecMap.putIfAbsent(topic, subscribers);
                if (oldSubscribers != null) {
                    subscribers = oldSubscribers;
                }
            }
            subscribers.add(subscriber);
        }
    }

    public void unSubscribe(String topic, EventSubscriber subscriber) {
        Set<EventSubscriber> subscribers = ecMap.get(topic);
        if (subscribers != null) {
            for (EventSubscriber eventSubscriber : subscribers) {
                if (eventSubscriber.getId().equals(subscriber.getId())) {
                    subscribers.remove(eventSubscriber);
                }
            }
        }
    }

    public void publishSync(EventInfo eventInfo) {
        Set<EventSubscriber> subscribers = ecMap.get(eventInfo.getTopic());
        if (subscribers != null) {
            for (EventSubscriber subscriber : subscribers) {
                eventInfo.setTopic(eventInfo.getTopic());
                try {
                    subscriber.getObserver().onObserved(eventInfo);
                } catch (Throwable t) {      // 防御性容错
                    LOGGER.error(" eventInfo:{}, subscriber:{}",
                            JSONUtils.toJSONString(eventInfo), JSONUtils.toJSONString(subscriber), t);
                }
            }
        }
    }

    public void publishAsync(final EventInfo eventInfo) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                String topic = eventInfo.getTopic();

                Set<EventSubscriber> subscribers = ecMap.get(topic);
                if (subscribers != null) {
                    for (EventSubscriber subscriber : subscribers) {
                        try {
                            eventInfo.setTopic(topic);
                            subscriber.getObserver().onObserved(eventInfo);
                        } catch (Throwable t) {     // 防御性容错
                            LOGGER.error(" eventInfo:{}, subscriber:{}",
                                    JSONUtils.toJSONString(eventInfo), JSONUtils.toJSONString(subscriber), t);
                        }
                    }
                }
            }
        });
    }
}
