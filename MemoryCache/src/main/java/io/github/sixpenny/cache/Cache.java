package io.github.sixpenny.cache;

/**
 * 对外提供接口类.
 * Created by liufengquan on 2018/2/26.
 */
public interface Cache {
    /**
     * 将值缓存进系统.
     * @param key 键
     * @param value 值
     */
    public void put(String key, Object value);

    /**
     * 将值缓存进系统， 存活时间为毫秒.
     * @param key 键
     * @param value 值
     * @param ttl 存活时间，毫秒
     */
    public void put(String key, Object value, Long ttl);

    /**
     * 根据键获取相应值
     * @param key 键
     * @return 值，不存在返回null
     */
    public Object get(String key);

    /**
     * 从系统中删除某个键
     * @param key 键
     * @return 被删除的键对应的值，键不存在于系统时返回null
     */
    public Object remove(String key);

    /**
     * 系统中是否存在键
     * @param key 键
     * @return 存在返回true，否则false
     */
    public boolean contain(String key);

    /**
     * 清空缓存系统里的所有键值
     */
    public void clear();

    /**
     * 缓存系统中的键数量
     * @return int
     */
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
