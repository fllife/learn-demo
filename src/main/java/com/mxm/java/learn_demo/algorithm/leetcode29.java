package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/19 11:10
 */
public class leetcode29 {
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        if (divisor == 1 || divisor == -1) {
            return divisor * dividend;
        }
        boolean postive = false;
        if (divisor > 0 && dividend > 0 || divisor < 0 && dividend < 0) {
            postive = true;
        }

        int result = 0;
        long dividendLong = Math.abs((long)dividend);
        long divisorLong = Math.abs((long)divisor);
        while (dividendLong >= divisorLong) {
            dividendLong -= divisorLong;
            result ++;
        }
        return (postive || result == 0) ? result : - result;
    }

    public static void main(String[] args) {
        System.out.println(divide(-2147483648, 2));
    }

}
