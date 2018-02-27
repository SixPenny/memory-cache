package io.github.sixpenny.cache.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.github.sixpenny.cache.Cache;
import io.github.sixpenny.cache.Configuration;

/**
 * 默认缓存实现.
 * Created by liufengquan on 2018/2/26.
 */
public class CacheImpl implements Cache {

    private ConcurrentHashMap<String, CacheItem> container;

    private AtomicLong totalQueryCount;
    private AtomicLong totalHitCount;
    private Integer capacity;

    public CacheImpl() {
        totalHitCount = new AtomicLong(0L);
        totalQueryCount = new AtomicLong(0L);
        capacity = Configuration.cacheSize;
        container = new ConcurrentHashMap<String, CacheItem>(capacity, 1f, Configuration.concurrencyLevel);
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
        if (ensureValid(cacheItem) && cacheItem != null) {
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

    private boolean ensureValid(CacheItem cacheItem) {
        if (cacheItem.isExpired()) {
            container.remove(cacheItem.getKey());
            return false;
        }
        return true;
    }

    /**
     * 检查容量，如果已满就按照清理策略清理一项.
     */
    private void enusureCapacity() {

    }

    public void clear() {
        container.clear();
    }

    public int size() {
        return container.size();
    }

    public String statisticRecord() {
        BigDecimal totalQuery = new BigDecimal(totalQueryCount.longValue());
        BigDecimal totalHit = new BigDecimal(totalHitCount.longValue());
        BigDecimal missingCount = totalQuery.subtract(totalHit);
        boolean isQueryZero = totalQuery.compareTo(BigDecimal.ZERO) == 0;
        BigDecimal hitRate = isQueryZero?
                BigDecimal.ONE
                : totalHit.divide(totalQuery, 2, RoundingMode.HALF_UP);
        BigDecimal missingRate = isQueryZero?
                BigDecimal.ZERO :
                missingCount.divide(totalQuery, 2, RoundingMode.HALF_UP);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalQueryCount", totalQuery.toPlainString());
        jsonObject.put("totalHitCount", totalHit.toPlainString());
        jsonObject.put("hitRate", hitRate.toPlainString());
        jsonObject.put("missingRate", missingRate.toPlainString());

        JSONArray keys = new JSONArray();
        Set<Map.Entry<String, CacheItem>> items = container.entrySet();
        for (Map.Entry<String, CacheItem> itemEntry : items) {
            CacheItem cacheItem = itemEntry.getValue();
            JSONObject itemJson = new JSONObject();
            itemJson.put("key", cacheItem.getKey());
            itemJson.put("value", cacheItem.getValue().toString());
            itemJson.put("hitCount", cacheItem.getHitCount());
            itemJson.put("createTime", cacheItem.getCreateTime());
            itemJson.put("lastUpdateTime", cacheItem.getLastUpdateTime());
            itemJson.put("expireTime", cacheItem.getExpireTime());
            keys.add(itemJson);
        }
        jsonObject.put("keys", keys);
        return jsonObject.toJSONString();
    }

    public AtomicLong getTotalQueryCount() {
        return totalQueryCount;
    }

    public void setTotalQueryCount(AtomicLong totalQueryCount) {
        this.totalQueryCount = totalQueryCount;
    }

    public AtomicLong getTotalHitCount() {
        return totalHitCount;
    }

    public void setTotalHitCount(AtomicLong totalHitCount) {
        this.totalHitCount = totalHitCount;
    }
}
