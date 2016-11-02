package me.j360.guava;

import me.j360.guava.cache.GuavaCache;
import me.j360.guava.cache.JdbcTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Package: me.j360.guava
 * User: min_xu
 * Date: 2016/11/1 下午7:33
 * 说明：
 *
 * 2.1 设置并发级别
 Guava Cache 是JDK7 ConcurrentHashMap的思路，先分N个Segment加锁，Segment下面才是HashMap。这样就把原本一个锁，打散成N个锁。
 但和ConcurrentHashMap默认16个锁不一样，GuavaCache默认是4个锁，自己看着设置。



 2.2 设置后台刷新
 如果你什么都不做，只是设置 CacheBuilder.newBuilder().refreshAfterWrite(30, TimeUnit.SECONDS)，那CacheLoader巧妇难为无米之炊之炊，还是要在当前线程里执行加载，还是会造成卡顿的。君不见CacheLoader中reload()的实现:

 return Futures.immediateFuture(load(key));
 所以，要后台刷新，一定要重载reload函数，给它一条执行的线程。

 CacheLoader里本身有一个静态函数，只要传给它原来的CacheLoader 和 一个executor，它会返回一个修饰器模式的包装类，重新实现reload()函数，以callable在executor里执行load()函数。

 public static CacheLoader asyncReloading(final CacheLoader loader, final Executor executor)
 但这也怕这包装类万一没被内联，白白多了层调用啊，所以还是不要这么懒，自己把它的reload()方法抄出来，抄到自己的CacheLoader 或者一个公用的CacheLoader父类里了。

 另外，如果executor給多个CacheLoader共用，最好用一个core pool size为1的，CachedPool风格的池。



 2.3 get()与uncheckGet()
 get()抛的是 Checked的 ExecutionException，另外还要做一次getCause()才能获得真正的异常，主要是load()里发生的Checked异常。

 getUnchecked()则抛的是UncheckedExecutionException，还是要做getCause()哈。当然如果严重错误Error，还是会抛ExecutionError的，但这不用在接口里声明。

 */
public class CacheMain {



    public static void main(String[] args) throws InterruptedException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        GuavaCache cache = new GuavaCache(jdbcTemplate);


        System.out.println(cache.get("aa"));

        cache.put("aa","aa");

        System.out.println(cache.get("aa"));

        TimeUnit.SECONDS.sleep(4);

        System.out.println(cache.get("aa"));

    }


}
