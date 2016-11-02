package me.j360.guava.cache;

import com.google.common.cache.*;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Package: me.j360.guava.cache
 * User: min_xu
 * Date: 2016/11/1 下午7:33
 * 说明:
 * Guava Cache有两种创建方式：

 　　1. cacheLoader
 　　2. callable callback
 *
 * 回收的参数：
 　　1. 大小的设置：CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
 　　2. 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
 　　3. 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
 　　4. 明确的删除：invalidate(key)  invalidateAll(keys)  invalidateAll()
 　　5. 删除监听器：CacheBuilder.removalListener(RemovalListener)


 refresh机制：
 　　1. LoadingCache.refresh(K)  在生成新的value的时候，旧的value依然会被使用。
 　　2. CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value
 　　3. CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache

 */
public class CacheLocal {

    //定义一个普通的Guava Cache，不需要用到load方法

    public static <K,V> LoadingCache<K,V> cache() {
        LoadingCache<K, V> cache = (LoadingCache<K, V>) CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.SECONDS)
                .maximumSize(100)
                //设置缓存容器的初始容量为10
                .initialCapacity(7)
                .refreshAfterWrite(1, TimeUnit.SECONDS)

                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(16)
                .removalListener(new RemovalListener<K, V>() {
                    @Override
                    public void onRemoval(RemovalNotification<K, V> rn) {
                        System.out.println(rn.getKey() + "被移除");

                    }
                })
                .build();
        return cache;
    }



    //实现自动刷新的cache
    public static  <K,V> LoadingCache<K,V> asyncCache(JdbcTemplate jdbcTemplate){
        //core=1+无界队列的线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 10,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        CacheLoader<K, V>  loader = CacheLoaderImpl.asyncReload(new CacheLoaderImpl(jdbcTemplate), pool);

        //
        return (LoadingCache<K, V>) CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.SECONDS)
                .maximumSize(100)
                //设置缓存容器的初始容量为10
                .initialCapacity(7)
                .refreshAfterWrite(1, TimeUnit.SECONDS)

                //设置并发级别为8，并发级别是指可以同时写缓存的线程数
                .concurrencyLevel(16)
                .removalListener(new RemovalListener<K, V>() {
                    @Override
                    public void onRemoval(RemovalNotification<K, V> rn) {
                        System.out.println(rn.getKey() + "被移除");

                    }
                })
                .build(loader);
    }


}
