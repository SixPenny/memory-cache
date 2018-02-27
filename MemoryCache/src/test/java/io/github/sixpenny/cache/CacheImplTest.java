package io.github.sixpenny.cache;

import io.github.sixpenny.cache.impl.CacheImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by liufengquan on 2018/2/27.
 */
public class CacheImplTest {

    private CacheImpl cache = new CacheImpl();

    @Test
    public void testCacheAddGet() {
        cache.put("a", "hahaha");
        Assert.assertTrue(cache.contain("a"));
        Assert.assertEquals(cache.size(), 1);

        String value = (String)cache.get("a");
        Assert.assertTrue(value.equals("hahaha"));

        cache.get("b");
        cache.get("b");
        Assert.assertTrue(cache.getTotalHitCount().longValue() == 1);
        Assert.assertTrue(cache.getTotalQueryCount().longValue() == 3);

        System.out.println(cache.statisticRecord());
    }
}
