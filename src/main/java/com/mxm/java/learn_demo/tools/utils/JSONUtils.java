package com.mxm.java.learn_demo.tools.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author maxianming
 * @date 2018/10/10 17:30
 */
@Slf4j
public class JSONUtils {
    
    /**
     * 判断字符串是否为
     * @author maxianming
     * @date 2018/10/10 17:32
     */
    public static boolean isJson(String str) {
        try {
            JSONObject.parseObject(str);
            return true;
        } catch (Exception e) {
            log.info("jsons格式错误", e);
            return false;
        }
    }


}
