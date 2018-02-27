package io.github.sixpenny.cache.impl;

import io.github.sixpenny.cache.CleanUpStrategy;

/**
 * LRU清理策略.
 * Created by liufengquan on 2018/2/27.
 */
public class LRUStrategy implements CleanUpContainer, CleanUpStrategy {
    public void addItem(CacheItem cacheItem) {

    }

    public void removeItem(CacheItem cacheItem) {

    }

    public CacheItem getHeadItemAndRemove() {
        return null;
    }

    public void clean() {

    }
}
