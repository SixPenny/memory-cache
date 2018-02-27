package io.github.sixpenny.cache.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.github.sixpenny.cache.Cache;
import io.github.sixpenny.cache.CleanUpStrategy;

/**
 * LRU清理策略.
 * Created by liufengquan on 2018/2/27.
 */
public class LRUStrategy implements CleanUpContainer, CleanUpStrategy {
    private Cache cache;
    private ConcurrentHashMap<String, CacheItem> container;

    public LRUStrategy(Cache cache) {
        this.cache = cache;
        container = new ConcurrentHashMap<String, CacheItem>();
    }
    public void addItem(CacheItem cacheItem) {
        container.put(cacheItem.getKey(), cacheItem);
    }

    public void removeItem(CacheItem cacheItem) {
        container.remove(cacheItem.getKey());
    }

    public CacheItem removalItemAccordingly() {
        CacheItem cacheItemToRemove = null;
        for (Map.Entry<String, CacheItem> cacheItemEntry : container.entrySet()) {
            CacheItem cacheItem = cacheItemEntry.getValue();
            if (cacheItemToRemove == null) {
                cacheItemToRemove = cacheItem;
            } else if (cacheItemToRemove.getLastUpdateTime() > cacheItem.getLastUpdateTime()){
                cacheItemToRemove = cacheItem;
            }
        }
        return cacheItemToRemove;
    }

    public void clean() {
        CacheItem cacheItem = removalItemAccordingly();
        if (cacheItem != null) {
            cache.remove(cacheItem.getKey());
        }
    }

    public void used(CacheItem cacheItem) {
        //do nothing
    }
}
