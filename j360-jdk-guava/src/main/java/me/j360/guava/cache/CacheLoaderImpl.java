package me.j360.guava.cache;

import com.google.common.cache.CacheLoader;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Package: me.j360.guava.cache
 * User: min_xu
 * Date: 2016/11/1 下午8:41
 * 说明：实现异步刷新的CacheLoader
 */
public class CacheLoaderImpl extends CacheLoader {

    private JdbcTemplate jdbcTemplate;

    public CacheLoaderImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public static <K, V> CacheLoader<K, V> asyncReload(final CacheLoader<K, V> loader,
                                                       final Executor executor) {
        checkNotNull(loader);
        checkNotNull(executor);

        return new CacheLoader<K, V>() {
            @Override
            public V load(K key) throws Exception {
                return loader.load(key);
            }

            @Override
            public ListenableFuture<V> reload(final K key, final V oldValue) throws Exception {
                ListenableFutureTask<V> task = ListenableFutureTask.create(new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        System.out.println("async impl load");
                        return loader.reload(key, oldValue).get();
                    }
                });
                executor.execute(task);
                return task;
            }

            @Override
            public Map<K, V> loadAll(Iterable<? extends K> keys) throws Exception {
                return loader.loadAll(keys);
            }
        };
    }

    @Override
    public Object load(Object o) throws Exception {
        System.out.println("impl load");
        return jdbcTemplate.getDefaultKey(String.valueOf(o));
    }
}
