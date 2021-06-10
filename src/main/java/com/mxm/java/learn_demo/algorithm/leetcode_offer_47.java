package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/10
 **/
public class leetcode_offer_47 {
    public int maxValue(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    dp[j] = grid[i][j] + Math.max(dp[j], dp[j - 1]);
                }
            }
        }

        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new leetcode_offer_47().maxValue(new int[][]{{1,3,1},{1,5,1  },{4,2,1}}));
    }
}
