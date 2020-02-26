package com.mxm.java.learn_demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maxianming
 * @date 2019/9/16 15:46
 */
public class leetcode13 {
    public static int romanToInt(String s) {
        int result = 0;
        Map<String, Integer> map = new HashMap() {{
            put("I", 1);
            put("IV", 4);
            put("V", 5);
            put("IX", 9);
            put("X", 10);
            put("XL", 40);
            put("L", 50);
            put("XC",90);
            put("C", 100);
            put("CD", 400);
            put("D", 500);
            put("CM", 900);
            put("M", 1000);

        }};
        if (map.get(s) != null) {
            return map.get(s);
        }
        for (int i = 0; i < s.length(); i ++  ) {
            if (i == s.length() - 1) {
                result += map.get(s.substring(i, i + 1));
            } else  {
                Integer num = map.get(s.substring(i, i+ 2));
                if ( num == null) {
                    result += map.get(s.substring(i, i + 1));
                } else {
                    result += num;
                    i++;
                }
            }

        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }
}
