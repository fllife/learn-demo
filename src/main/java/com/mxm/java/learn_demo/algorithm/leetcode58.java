package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/17 15:47
 */
public class leetcode58 {
    public int lengthOfLastWord(String s) {
        if (s.length() <= 0) {
            return 0;
        }
        String[] str = s.split(" ");
        if (str.length == 0) {
            return 0;
        }
        return str[ str.length - 1].length();
    }

    public static void main(String[] args) {
        System.out.println(new leetcode58().lengthOfLastWord("Hello World"));
    }
}
