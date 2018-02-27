package io.github.sixpenny.cache.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import io.github.sixpenny.cache.Cache;
import io.github.sixpenny.cache.Configuration;

/**
 * 默认缓存实现.
 * Created by liufengquan on 2018/2/26.
 */
public class CacheImpl implements Cache {

    private ConcurrentHashMap<String, CacheItem> container = new ConcurrentHashMap<String, CacheItem>();

    private AtomicLong totalQueryCount;
    private AtomicLong totalHitCount;

    public CacheImpl() {
        totalHitCount = new AtomicLong(0L);
        totalQueryCount = new AtomicLong(0L);
    }

    public void put(String key, Object value) {
        this.put(key, value, Configuration.ttl);
    }

    public void put(String key, Object value, Long ttl) {
        CacheItem cacheItem = new CacheItem(key, value, ttl);
        container.put(key, cacheItem);
    }

    public Object get(String key) {
        totalQueryCount.incrementAndGet();
        CacheItem cacheItem = container.get(key);
        if (cacheItem != null) {
            totalHitCount.incrementAndGet();
            cacheItem.addHitCount();
            return cacheItem.getValue();
        }
        return null;
    }

    public boolean contain(String key) {
        return container.containsKey(key);
    }

    public Object remove(String key) {
        CacheItem cacheItem = container.remove(key);
        if (cacheItem != null) {
            return cacheItem.getValue();
        }
        return null;
    }

    public void clear() {
        container.clear();
    }

    public int size() {
        return container.size();
    }
}
