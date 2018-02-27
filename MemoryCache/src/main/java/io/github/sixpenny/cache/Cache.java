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
}