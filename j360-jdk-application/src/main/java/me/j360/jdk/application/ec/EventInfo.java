package me.j360.jdk.application.ec;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with j360-jdk -> me.j360.jdk.application.ec.
 * User: min_xu
 * Date: 2015/11/18
 * Time: 11:24
 * 说明：
 */
public class EventInfo {
    private String topic;
    private Map<String, Object> params;

    public EventInfo(String topic) {
        this.topic = topic;
    }

    public void setParam(String key, Object value) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        params.put(key, value);
    }

    public Object removeParam(String key) {
        if (params != null) {
            return params.remove(key);
        }
        return null;
    }

    public Object getParam(String key) {
        if (params != null) {
            return params.get(key);
        }
        return null;
    }

    public Map<String, Object> getParams() {
        return params == null ? new HashMap<String, Object>() : params;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
