package io.github.sixpenny.cache;

import io.github.sixpenny.cache.impl.CacheImpl;

/**
 * Created by liufengquan on 2018/2/26.
 */
public class CacheFactory {

    public static Cache getCache() {
        return CacheContainer.cache;
    }

    static class CacheContainer{
        static Cache cache = new CacheImpl();
    }
}
