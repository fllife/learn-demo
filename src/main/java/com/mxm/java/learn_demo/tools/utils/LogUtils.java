package com.mxm.java.learn_demo.tools.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maxianming
 * date 2018/6/27 19:43
 */
@Slf4j
public class LogUtils {

    /**
     * @author maxianming
     * date 2018/6/7 19:06
     */
     public static String jointMethodParam(String[] paramNames, Object[] paramValues) {
        StringBuffer paramStr = new StringBuffer();
        if (paramNames != null && paramNames.length > 0) {
            for (int i = 0; i < paramNames.length; i++) {
                paramStr.append(paramNames[i] + ":" + JSON.toJSONString(paramValues[i]) + ",");
            }
        }
        return paramStr.toString();
    }

}
