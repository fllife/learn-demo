package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/11 17:40
 */
public class leetcode6 {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        String[] strs = new String[numRows];

        boolean flag = true;
        int line = 0;
        for (int i = 0; i < s.length(); i++) {
            strs[line] = (strs[line] == null) ? s.charAt(i)+ "" : strs[line] + s.charAt(i);

            if (flag) {
                line++;
                if (line >= numRows) {
                    if (numRows - 2 <= 0) {
                        line = 0;
                    } else {
                        flag = false;
                        line = numRows - 2;
                    }
                }
            } else {
                line-- ;
                if (line <= 0) {
                    flag = true;
                    line = 0;
                }
            }
        }

        StringBuffer result = new StringBuffer();
        for (int j = 0; j < numRows; j++) {
            if (strs[j] != null) {
                result.append(strs[j]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("ABCD", 2));
    }
}
