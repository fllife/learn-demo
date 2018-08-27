package com.mxm.java.learn_demo.tools.monitor.alert;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author maxianming
 * @date 2018/8/15 15:27
 */
@Data
@ConfigurationProperties("monitor.alert.dingtalk")
public class DingtalkProperties {

    /**
     * 存储异常信息个数
     * @author maxianming
     * @param
     * @return
     * @date 2018/8/16 7:49
     */
    private Integer capacity = 100;

    /**
     *  拉取时间间隔 ms
     */
    private long pullInterval = 5000;
    
    /**
     * 通知地址
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 7:50
     */
    private String url;

    /**
     * 应用名称
     * @author maxianming
     * @param
     * @return 
     * @date 2018/8/16 7:49
     */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 环境名称
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 7:49
     */
    @Value("${spring.profiles.active}")
    private String env;
    
    /**
     * 通知的人手机号
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 7:50
     */
    private List<String>  phone;

    /**
     * 是否通知所有人
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 7:50
     */
    private boolean atAll = false;
    /**
     * 级别 SHORT:输出简短异常栈 FULL:输出全部异常栈
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/8/16 9:27
     */
    private Level level = Level.SHORT;
    public enum Level {
        SHORT,FULL
    }
}
