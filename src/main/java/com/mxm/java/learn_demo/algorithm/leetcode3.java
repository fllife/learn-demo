package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/10 13:53
 */
public class leetcode3 {
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        String sequence = "";
        int end = 0;
        while(end < s.length()) {
            int index = sequence.indexOf(s.charAt(end) + "");
            if (index == -1) {
                sequence += s.charAt(end);
                if (sequence.length() > maxLength) {
                    maxLength = sequence.length();
                }
            } else {

                if (index + 1 == s.length()) {
                    sequence = "";
                } else {
                    sequence = sequence.substring(index + 1) + s.charAt(end);
                }
            }
            end ++ ;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("b"));
    }
}
