package me.j360.jdk.application.ec;

/**
 * Created with j360-jdk -> me.j360.jdk.application.ec.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:24
 * 说明：
 */
public class EventSubscriber {
    public EventSubscriber(String id, Observer observer) {
        this.id = id;
        this.observer = observer;
    }

    private String id;

    private Observer observer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
}
