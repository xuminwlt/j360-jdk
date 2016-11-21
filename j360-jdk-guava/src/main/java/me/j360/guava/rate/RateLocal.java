package me.j360.guava.rate;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Package: me.j360.guava.rate
 * User: min_xu
 * Date: 2016/11/2 下午3:53
 * 说明：令牌桶实现
 * Guava已实现了一个性能非常好的RateLimiter，基本不需要我们再费心实现。但其中有一些实现的细节，需要我们留意：

 1. 支持桶外预借的突发

 突发，原本是指如果单位时间的前半段流量较少，桶里会积累一些令牌，然后支持来一波大的瞬时流量，将前面积累的令牌消耗掉。

 但在RateLimiter的实现里，还多了个桶外预借（我自己给他的命名），就是即使桶里没有多少令牌，你也可以消耗一波大的，然后桶里面在时间段内都没有新令牌。比如桶的容量是5，桶里面现在只有1个令牌，如果你要拿5个令牌，也可以，清了桶里的一个令牌，再预借4个。然后再过800毫秒，桶里才会出现新令牌。

 可见，Guava版的RateLimiter对突发的支持，比原版的两种算法都要大，你几乎随时都可以一次过消费burst个令牌，不管现在桶里有没有积累的令牌。

 不过有个副作用，就是如果前面都没什么流量，桶里累积了5个令牌，则你其实可以一次过消费10个令牌。。。不过那么一下，超借完接下来还是固定速率的，直到还清了旧账，才可能再来那么一下。

 2. 支持等待可用令牌与立刻返回两种接口

 3. 单位时段是秒，这有点不太好用，不支持设定5分钟的单位。

 4. 发令牌的计算粒度是MicroSeconds，也就是最多支持一百万的QPS。
 */
public class RateLocal {



    public void RateSample(){
        RateLimiter.create(1000,10, TimeUnit.SECONDS);
    }
}
