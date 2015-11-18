package me.j360.jdk.application.ec;

/**
 * Created with j360-jdk -> me.j360.jdk.application.ec.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:24
 * 说明：
 */
public interface EventCenter {
    /**
     * 订阅主题
     *
     * @param topics
     * @param subscriber
     */
    public void subscribe(EventSubscriber subscriber, String... topics);

    /**
     * 取消订阅主题
     *
     * @param topic
     * @param subscriber
     */
    public void unSubscribe(String topic, EventSubscriber subscriber);

    /**
     * 同步发布主题消息
     *
     * @param eventInfo
     */
    public void publishSync(EventInfo eventInfo);

    /**
     * 异步发送主题消息
     *
     * @param eventInfo
     */
    public void publishAsync(EventInfo eventInfo);
}
