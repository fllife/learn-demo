package com.mxm.java.learn_demo.tools.monitor.demo;

import com.mxm.java.learn_demo.tools.monitor.ExceptionMesssage;
import com.mxm.java.learn_demo.tools.monitor.ExceptionReport;
import com.mxm.java.learn_demo.tools.monitor.StorageInMemory;
import com.mxm.java.learn_demo.tools.monitor.alert.DingtalkProperties;
import com.mxm.java.learn_demo.tools.monitor.alert.DingtalkPush;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author maxianming
 * @date 2018/8/15 18:13
 */
public class TexTest {

    public static void main(String[] args) {
        ExceptionReport exceptionNotify = new StorageInMemory(100);
        DingtalkProperties dingtalkProperties = new DingtalkProperties();
        dingtalkProperties.setApplicationName("");
        dingtalkProperties.setEnv("无锡测试");
        dingtalkProperties.setPhone(new ArrayList<>());
        dingtalkProperties.setUrl("");
        new DingtalkPush(exceptionNotify, dingtalkProperties);
        ExceptionMesssage exceptionMesssage = new ExceptionMesssage();
        exceptionMesssage.setOccurTime(new Date());
        exceptionMesssage.setThrowable(new NullPointerException());
    }
}
