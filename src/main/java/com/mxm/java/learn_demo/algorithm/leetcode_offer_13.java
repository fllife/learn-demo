package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/18
 **/
public class leetcode_offer_13 {
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        int[][] grid = new int[m][n];
        return dfs(grid, 0, 0, k);
    }

    private int dfs(int[][] grid, int i, int j, int k) {
        if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || digitSum(i) + digitSum(j) > k || grid[i][j] == -1) {
            return 0;
        }
        grid[i][j] = -1;
        return 1 + dfs(grid, i + 1, j, k) + dfs(grid, i - 1, j, k) + dfs(grid, i,j - 1,  k)+ dfs(grid, i, j + 1, k);
    }

    public int digitSum(int doubleDigit) {
        if (doubleDigit < 10) {
            return doubleDigit;
        } else if (doubleDigit == 100) {
            return 1;
        } else {
            int theUnit = doubleDigit % 10;
            int decade = doubleDigit / 10;
            return theUnit + decade;
        }
    }
}
