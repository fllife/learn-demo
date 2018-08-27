package com.mxm.java.learn_demo.common.utils;

import java.util.UUID;

/**
 * @author maxianming
 * @date 2018/6/9 16:14
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
