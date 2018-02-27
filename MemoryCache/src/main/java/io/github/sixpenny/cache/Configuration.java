package io.github.sixpenny.cache;

/**
 * Created by liufengquan on 2018/2/26.
 */
public class Configuration {

    private Long ttl = 5 * 60 * 1000L;

    private Integer cacheSize = Integer.MAX_VALUE;

    private Integer concurrencyLevel = 1 << 4;

    public static final String CLEAN_UP_STRATEGY_LRU = "LRU";
    public static final String CLEAN_UP_STRATEGY_FIFO = "FIFO";
    public static final String CLEAN_UP_STRATEGY_LFU = "LFU";

    private String cleanUpStrategy = "";



    //TODO 初始化配置
    public void config() {

    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Integer cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Integer getConcurrencyLevel() {
        return concurrencyLevel;
    }

    public void setConcurrencyLevel(Integer concurrencyLevel) {
        this.concurrencyLevel = concurrencyLevel;
    }

    public String getCleanUpStrategy() {
        return cleanUpStrategy;
    }

    public void setCleanUpStrategy(String cleanUpStrategy) {
        this.cleanUpStrategy = cleanUpStrategy;
    }
}
