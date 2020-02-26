package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/18 18:19
 */
public class leetcode69 {
    public int mySqrt(int x) {
        for (int i = 1; i <= x; i++) {
            if (i * i == x) {
                return i;
            } else if (i * i > x) {
                return i - 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode69().mySqrt(64));
    }
}
