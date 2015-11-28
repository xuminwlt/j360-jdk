package me.j360.jdk.application.core.common.loadbalance;

import me.j360.jdk.application.core.common.support.Config;

import java.util.List;

/**
 * Robert HG (254963746@qq.com) on 3/25/15.
 */
public interface LoadBalance {

    public <S> S select(Config config, List<S> shards, String seed);

}
