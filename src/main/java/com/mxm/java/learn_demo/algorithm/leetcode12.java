package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/12 17:24
 */
public class leetcode12 {

    public static String intToRoman(int num) {
        StringBuffer result = new StringBuffer();
        String[] k = new String[]{ "I", "IV", "V", "IX",  "X", "XX", "XXX", "XL", "L", "XC", "C", "CC",  "CCC", "CD",  "D", "CM", "M"};
        int[] v = new int[]{        1,   4,    5,    9,   10,   20,   30,    40,   50,   90, 100,  200,   300,    400,  500,  900, 1000};
        while (num > 0) {
            int temp = 0;
            for (int i = 0; i < v.length; i++) {
                if (num < v[i] ) {
                    temp = v[i - 1];
                    result.append(k[i - 1]);
                    break;
                } else if (num == v[i]){
                    temp = v[i];
                    result.append(k [i]);
                    break;
                } else {
                    if (i == v.length - 1) {
                        temp = v[i];
                        result.append(k [i]);
                        break;
                    }
                }
            }
            num = num - temp;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }
}
