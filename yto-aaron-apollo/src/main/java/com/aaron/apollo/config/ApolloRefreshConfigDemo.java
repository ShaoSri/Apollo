package com.aaron.apollo.config;


import com.aaron.apollo.common.ApolloRefreshConfig;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * @ClassName: ApolloRefreshConfig
 * @Description : 实时更新配置
 * @Author Aaron
 * @Date 2018/6/28
 * @Version 1.0
 */
@Component
public class ApolloRefreshConfigDemo {

    private static final Logger logger = LoggerFactory.getLogger(ApolloRefreshConfigDemo.class);

    @Autowired
    private ApolloRefreshConfig apolloRefreshConfig;

    @Autowired
    private ApolloConfigDemo apolloConfigDemo;

    @ApolloConfig("application")
    private Config config;

    @Autowired
    private RefreshScope refreshScope;

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent changeEvent) {
        logger.info("before refresh {}", apolloConfigDemo.toString());
        refreshScope.refresh("apolloConfigDemo");
        logger.info("after refresh {}", apolloConfigDemo.toString());
    }

    /**
     * apollo
     * @param changeEvent
     */
    @ApolloConfigChangeListener("application1")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {

        ConfigChange change = changeEvent.getChange("test");
        logger.info(String.format("Found change - key: %s, oldValue: %s,"
                + " newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }


}
