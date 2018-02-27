package io.github.sixpenny.cache.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;

import io.github.sixpenny.cache.Cache;
import io.github.sixpenny.cache.CleanUpStrategy;

/**
 * 先进先出清理策略.
 * Created by liufengquan on 2018/2/27.
 */
public class FIFOStrategy implements CleanUpContainer, CleanUpStrategy {
    private LinkedHashMap<String, CacheItem> cacheItems = new LinkedHashMap<String, CacheItem>();
    private Cache cache;

    public FIFOStrategy(Cache cache) {
        this.cache = cache;
    }
    synchronized public void addItem(CacheItem cacheItem) {
        cacheItems.put(cacheItem.getKey(), cacheItem);
    }

    synchronized public void removeItem(CacheItem cacheItem) {
        cacheItems.remove(cacheItem.getKey());
    }

    synchronized public CacheItem removalItemAccordingly() {
        Iterator<String> iterator = cacheItems.keySet().iterator();
        if (iterator.hasNext()) {
            return cacheItems.get(iterator.next());
        }
        return null;
    }

    synchronized public void used(CacheItem cacheItem) {
        //do nothing
    }

    synchronized public void clean() {
        CacheItem item = removalItemAccordingly();
        if (item != null) {
            this.removeItem(item);
            cache.remove(item.getKey());
        }
    }
}
