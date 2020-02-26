package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/12 15:14
 */
public class leetcode9 {
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else if (x >= 0 && x < 10) {
            return true;
        }
        int left = x;
        int right = 0;
        while (x != 0) {
            right = right * 10 + x % 10;
            x = x / 10;
        }
        return (left == right) ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome( 2001));
    }
}
