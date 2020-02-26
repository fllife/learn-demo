package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/10 15:20
 */
public class leetcode5 {
    public static String longestPalindrome(String s) {
        int n = s.length();
        String res = "";
        boolean[] P = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                P[j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || P[j - 1]);
                if (P[j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbbad"));
    }
}
