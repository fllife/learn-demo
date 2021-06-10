package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/3
 **/
public class leetcode171 {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        int k = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
             int temp = columnTitle.charAt(i) - 'A' + 1;
             result += temp * k;
             k *= 26;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode171().titleToNumber(""));
    }
}
