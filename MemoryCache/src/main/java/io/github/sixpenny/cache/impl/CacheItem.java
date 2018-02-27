package io.github.sixpenny.cache.impl;

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
    private Long ttl;

    CacheItem(String key, Object value, Long ttl) {
        this.key = key;
        this.value = value;
        Long currentTimeMillis = System.currentTimeMillis();
        this.ttl = ttl;
        this.expireTime =  + ttl;
        this.createTime = currentTimeMillis;
        this.hitCount = 0L;
        this.lastUpdateTime = currentTimeMillis;
    }

    String getKey() {
        return key;
    }

    Object getValue() {
        return value;
    }

    Long getExpireTime() {
        return expireTime;
    }

    Long getHitCount() {
        return hitCount;
    }

    synchronized void  addHitCount() {
        this.hitCount++;
    }

    public Long getCreateTime() {
        return createTime;
    }

    Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void refresh() {
        Long currentTimeMills = System.currentTimeMillis();
        lastUpdateTime = currentTimeMills;
        expireTime = currentTimeMills + ttl;

    }

    public boolean isExpired() {
        return expireTime < System.currentTimeMillis();
    }

}
