package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/18 14:36
 */
public class leetcode64 {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0;  i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (i != 0 || j != 0) {
                    if (i == 0 && j > 0) {
                        grid[i][j] += grid[i][j - 1];
                    } else if (j == 0 && i > 0) {
                        grid[i][j] += grid[i - 1][j];
                    } else {
                        grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                    }
                }
            }
        }
        return grid[n - 1][m - 1];
    }


    public static void main(String[] args) {
        System.out.println(new leetcode64().minPathSum(new int[][]{{1,3,1}}));
    }

}
