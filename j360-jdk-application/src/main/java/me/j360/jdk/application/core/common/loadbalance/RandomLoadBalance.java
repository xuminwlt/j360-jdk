package me.j360.jdk.application.core.common.loadbalance;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ����ؾ���㷨
 * Robert HG (254963746@qq.com) on 3/25/15.
 */
public class RandomLoadBalance extends AbstractLoadBalance {

    @Override
    protected <S> S doSelect(List<S> shards, String seed) {
        return shards.get(ThreadLocalRandom.current().nextInt(shards.size()));
    }
}
