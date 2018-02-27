package io.github.sixpenny.cache;

/**
 * 对外提供接口类.
 * Created by liufengquan on 2018/2/26.
 */
public interface Cache {
    public void put(String key, Object value);

    public void put(String key, Object value, Long ttl);

    public Object get(String key);

    public Object remove(String key);

    public boolean contain(String key);

    public void clear();

    public int size();

    /**
     * 获取缓存系统的统计信息.
     * 格式：
     *  {
     *  "totalHitCount": "1",
     *  "totalQueryCount": "3",
     *  "keys": [{
     *      "hitCount": 1,
     *      "expireTime": 300000,
     *      "createTime": 1519699861567,
     *      "value": "hahaha",
     *      "key": "a",
     *      "lastUpdateTime": 1519699861567
     *      }
     *   ],
     *  "missingRate": "0.67",
     *  "hitRate": "0.33"
     *  }
     * @return
     */
    public String statisticRecord();
}
