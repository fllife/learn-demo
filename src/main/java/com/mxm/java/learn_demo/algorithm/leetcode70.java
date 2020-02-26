package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/21 10:26
 */
public class leetcode70 {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if ( n == 2) {
            return 2;
        }
        int preOne = 2;
        int preTwo = 1;
        int number = 2;
        while (++number < n) {
            int temp = preOne;
            preOne = preTwo + preOne;
            preTwo = temp;
        }
        return preOne + preTwo;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode70().climbStairs(5));
    }
}
