package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/12 10:16
 */
public class leetcode7 {
    public static int reverse(int x) {
        if ( -10 < x && x < 10) {
            return x;
        }
        StringBuffer result = new StringBuffer();
        String s = String.valueOf(x);
        boolean positive = true;
        boolean preZero = true;
        for (int i = s.length() - 1; i >= 0; i --) {
            char c = s.charAt(i);
            if ('0' == c && preZero) {
                preZero = true;
                continue;
            } else {
                preZero = false;
            }
            if ('-' == c) {
                positive = false;
            } else {
                result.append(s.charAt(i));
            }
        }
        try {
            return positive ? Integer.valueOf(result.toString()) : Integer.valueOf("-" + result.toString());
        } catch (Exception e) {
            return 0;
        }

    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }
}
