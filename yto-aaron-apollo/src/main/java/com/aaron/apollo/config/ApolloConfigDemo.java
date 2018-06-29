package com.aaron.apollo.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName: ApolloConfig
 * @Description : apollo配置
 * @Author Aaron
 * @Date 2018/6/28
 * @Version 1.0
 */

@ConfigurationProperties(prefix = "redis.cache")
@Component("apolloConfigDemo")
@RefreshScope
public class ApolloConfigDemo {

    private static final Logger logger = LoggerFactory.getLogger(ApolloConfigDemo.class);

    private int expireSeconds;
    private String clusterNodes;
    private int commandTimeout;

    private Map<String, String> someMap = Maps.newLinkedHashMap();
    private List<String> someList = Lists.newLinkedList();

    @PostConstruct
    private void initialize() {
        logger.info(
                "SampleRedisConfig initialized - expireSeconds: {}, clusterNodes: {}, commandTimeout: {}, someMap: {}, someList: {}",
                expireSeconds, clusterNodes, commandTimeout, someMap, someList);
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public Map<String, String> getSomeMap() {
        return someMap;
    }

    public List<String> getSomeList() {
        return someList;
    }

    @Override
    public String toString() {
        return String.format(
                "[SampleRedisConfig] expireSeconds: %d, clusterNodes: %s, commandTimeout: %d, someMap: %s, someList: %s",
                expireSeconds, clusterNodes, commandTimeout, someMap, someList);
    }
}