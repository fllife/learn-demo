package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/19 10:30
 */
public class leetcode28 {
    public static int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }
        int result = -1;
        int j = 0;
        char first = needle.charAt(0);
        int nextStart = -1;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == first && j != 0 && nextStart == -1) {
                nextStart = i;
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (j == needle.length() - 1) {
                    return i - needle.length() + 1;
                } else {
                    j++;
                }
            } else {
                j = 0;
                if (nextStart != -1) {
                    i = nextStart - 1;
                    nextStart = -1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(strStr("aabaaabaaac", "aabaaac"));
    }

}