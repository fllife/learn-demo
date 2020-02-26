package com.mxm.java.learn_demo;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{   
        public static void main(String[] args) {
            Map<String, Object> resourceInfo = new HashMap<>();
            resourceInfo.put("resourceIndexCode", "324sdgdfsfgt");
            resourceInfo.put("resourceType", "acsDevice");
            List<String> list = new ArrayList<>();
            list.add("1");
            resourceInfo.put("channelNos", list);
            System.out.println(JSON.toJSONString(resourceInfo)); ;
        }


    public static int ip2IntegerSkip2(String ipv4) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(ipv4);
        int result = 0;
        int counter = 0;
        int skip = 0;
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group());
            if (skip <= 1) {
                skip++;
                continue;
            }
            result = (value << 8 * (1 - counter++)) | result;
        }
        return result;
    }
}
