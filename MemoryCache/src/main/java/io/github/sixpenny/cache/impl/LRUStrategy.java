package io.github.sixpenny.cache.impl;

import java.util.Map;

import io.github.sixpenny.cache.CleanUpStrategy;

/**
 * LRU清理策略.
 * Created by liufengquan on 2018/2/27.
 */
public class LRUStrategy implements CleanUpContainer, CleanUpStrategy {
    private CacheImpl cacheImpl;

    public LRUStrategy(CacheImpl cacheImpl) {
        this.cacheImpl = cacheImpl;
    }
    public void addItem(CacheItem cacheItem) {

    }

    public void removeItem(CacheItem cacheItem) {

    }

    public CacheItem removalItemAccordingly() {
        CacheItem cacheItemToRemove = null;
        for (Map.Entry<String, CacheItem> cacheItemEntry : cacheImpl.container.entrySet()) {
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
            cacheImpl.remove(cacheItem.getKey());
        }
    }

    public void used(CacheItem cacheItem) {
        cacheItem.refresh();
    }


}
