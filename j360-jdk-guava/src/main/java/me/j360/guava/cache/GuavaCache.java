package me.j360.guava.cache;

import com.google.common.cache.LoadingCache;

/**
 * Package: me.j360.guava.cache
 * User: min_xu
 * Date: 2016/11/2 上午11:23
 * 说明：定义guava cache的bean
 */
public class GuavaCache {


    private LoadingCache<String,Object> caches;

    public GuavaCache(){

    }

    public GuavaCache(JdbcTemplate jdbcTemplate){
        caches = CacheLocal.asyncCache(jdbcTemplate);
    }

    public Object get(String key){
        return caches.getUnchecked(key);
    }

    public void put(String key,String value){
        caches.put(key,value);
    }

}
