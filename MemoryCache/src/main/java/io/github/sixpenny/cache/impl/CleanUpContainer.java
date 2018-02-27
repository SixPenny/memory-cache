package io.github.sixpenny.cache.impl;

/**
 * Created by liufengquan on 2018/2/27.
 */
public interface CleanUpContainer {
    public void addItem(CacheItem cacheItem);

    public void removeItem(CacheItem cacheItem);

    public CacheItem removalItemAccordingly();

    /**
     * 使用时更新.
     */
    public void used(CacheItem cacheItem);
}
