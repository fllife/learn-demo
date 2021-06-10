package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/5/31
 **/
public class leetcode122_boundary_value {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int firstIndex = 0;
        int lastIndex = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] > prices[lastIndex]) {
                lastIndex = i;
            } else if (prices[i] < prices[lastIndex]) {
                profit += prices[lastIndex] - prices[firstIndex];
                firstIndex = lastIndex = i;
            }
        }
        profit += prices[lastIndex] - prices[firstIndex];
        return  profit;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode122_boundary_value().maxProfit(new int[]{6,1,3,2,4,7}));
    }
}
