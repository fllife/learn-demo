package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/5/31
 **/
public class leetcode121 {

    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int result = Integer.MIN_VALUE;
        int afterMax = Integer.MIN_VALUE;
        int beforeMin = Integer.MAX_VALUE;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (prices[i] > afterMax) {
                for (int j = 0; j < i; j++) {
                    if (prices[j] < beforeMin) {
                        result = prices[i] - prices[j] > result ? prices[i] - prices[j] : result;
                        beforeMin = prices[j];
                    }
                }
                beforeMin = Integer.MAX_VALUE;
                afterMax = prices[i];
            }
        }
        return result < 0 ? 0 : result;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode121().maxProfit(new int[]{1,2,3,4}));
    }
}
