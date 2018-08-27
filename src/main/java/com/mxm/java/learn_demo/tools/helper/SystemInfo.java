package com.mxm.java.learn_demo.tools.helper;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author maxianming
 * @date 2018/8/15 16:33
 */
@Slf4j
public class SystemInfo {

    private static final String UNKNOWN = "UNKNOWN";

    private static InetAddress inetAddress =  null;
    private static String hostName = null;
    private static String ip = null;
    static {
        try {
            inetAddress = InetAddress.getLocalHost();
            hostName = inetAddress.getHostName();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
           log.error("获取本机信息异常",e);
        }
    }

    public static String getHostName() {
        return hostName != null ? hostName : UNKNOWN;
    }


    public static String getHostIp(){
        return ip != null ? ip : UNKNOWN;
    }
}
