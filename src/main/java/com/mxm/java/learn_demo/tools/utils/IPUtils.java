package com.mxm.java.learn_demo.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author maxianming
 * @date 2018/8/31 8:41
 */
@Slf4j
public class IPUtils {

    /**
     * 获取IP地址
     * @author maxianming
     * @param
     * @return 
     * @date 2018/10/16 9:24
     */
    public static String getIp() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return getIpAddress(request);
    }

    /**
	 * 获取ip地址
     * @author maxianming
     * @param 
     * @return 
     * @date 2018/10/8 13:04
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        log.debug("获取x-forwarded-for:{}",ip);
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            log.debug("获取Proxy-Client-IP:{}",ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
            log.debug("获取WL-Proxy-Client-IP:{}",ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.debug("获取HTTP_CLIENT_IP:{}",ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.debug("获取HTTP_X_FORWARDED_FOR:{}",ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            log.debug("获取RemoteAddr:{}",ip);
        }
        return StringUtils.substringBefore(ip, ",");
    }
}
