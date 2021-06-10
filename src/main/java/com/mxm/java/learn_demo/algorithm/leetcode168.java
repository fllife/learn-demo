package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/2
 **/
public class leetcode168 {

    public String convertToTitle(int columnNumber) {
        String str = new String();
        int index = 26;
        while (columnNumber >= index) {
            char c;
            if (columnNumber == index) {
                c = 'Z';
            } else {
                c = (char) ('A' + columnNumber % index);
            }
            str += c;
            index = index * 26;
        }
        String result = "";
        for (int i = str.length() - 1; i >= 0 ; i--) {
            result += str.charAt(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode168().convertToTitle(2));
    }
}
