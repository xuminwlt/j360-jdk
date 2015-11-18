package me.j360.jdk.application.core.common.support;

/**
 *
 */
public class KVPair<Key, Value> {
    private Key key;
    private Value value;

    public KVPair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}

