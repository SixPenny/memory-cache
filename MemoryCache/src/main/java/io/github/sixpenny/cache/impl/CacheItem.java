package io.github.sixpenny.cache.impl;

import io.github.sixpenny.cache.Configuration;

/**
 * Created by liufengquan on 2018/2/26.
 */
public class CacheItem {

    private String key;
    private Object value;

    private Long expireTime;
    private Long hitCount;
    private Long createTime;
    private Long lastUpdateTime;

    CacheItem(String key, Object value, Long ttl) {
        this.key = key;
        this.value = value;
        Long currentTimeMillis = System.currentTimeMillis();
        this.expireTime =  + ttl;
        this.createTime = currentTimeMillis;
        this.hitCount = 0L;
        this.lastUpdateTime = currentTimeMillis;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }

    synchronized public void  addHitCount() {
        this.hitCount++;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
