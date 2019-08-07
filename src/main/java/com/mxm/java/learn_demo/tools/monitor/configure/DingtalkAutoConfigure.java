package com.mxm.java.learn_demo.tools.monitor.configure;

import com.mxm.java.learn_demo.tools.monitor.exception.ExceptionReport;
import com.mxm.java.learn_demo.tools.monitor.exception.StorageInMemory;
import com.mxm.java.learn_demo.tools.monitor.alert.DingtalkProperties;
import com.mxm.java.learn_demo.tools.monitor.alert.DingtalkPush;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author maxianming
 * @date 2018/8/15 15:23
 */
@Configuration
@EnableConfigurationProperties(DingtalkProperties.class)
@ConditionalOnProperty(value = "monitor.alert.dingtalk.enble",havingValue ="true")
public class DingtalkAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    public ExceptionReport exceptionNotify(DingtalkProperties dingtalkProperties) {
        return new StorageInMemory(dingtalkProperties.getCapacity());
    }

    @Bean
    @ConditionalOnMissingBean
    public DingtalkPush dingtalkPush(ExceptionReport exceptionNotify, DingtalkProperties dingtalkProperties) {
        return new DingtalkPush(exceptionNotify, dingtalkProperties);
    }


}
