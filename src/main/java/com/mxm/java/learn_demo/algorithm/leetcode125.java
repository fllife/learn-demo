package com.mxm.java.learn_demo.algorithm;

import java.util.Locale;

/**
 * @author: Jack.Ma
 * @date: 2021/5/31
 **/
public class leetcode125 {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return true;
        }
        s = s.trim().toLowerCase();
        for (int i = 0, j = s.length() - 1 ; i < j;) {
            boolean compare = true;
            if (skipChar(s.charAt(i))) {
                i++;
                compare = false;
            } else if (skipChar(s.charAt(j))) {
                j--;
                compare = false;
            }
            if (compare) {
                if (s.charAt(i) != s.charAt(j)) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }

    public boolean skipChar(char c) {
        if (('0' <= c && c <= '9') || ('a' <= c && c <= 'z')) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(new leetcode125().isPalindrome(" "));
    }
}
