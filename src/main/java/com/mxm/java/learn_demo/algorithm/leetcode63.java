package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/18 13:36
 */
public class leetcode63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    if ((i > 0 && obstacleGrid[i - 1][j] == 0) || (j > 0 &&  obstacleGrid[i ][j -1] == 0 )) {
                        obstacleGrid[i][j] = 0;
                    } else {
                        obstacleGrid[i][j] = 1 - obstacleGrid[i][j];
                    }

                } else {
                    if ( obstacleGrid [i] [j] == 1) {
                        obstacleGrid [i] [j] = 0;
                    } else {
                        obstacleGrid [i] [j] = obstacleGrid [i] [j - 1] + obstacleGrid[i-1] [j];
                    }

                }

            }
        }
        return obstacleGrid[n - 1][m - 1];
    }

    public static void main(String[] args) {
        System.out.println(new leetcode63().uniquePathsWithObstacles(new int[][]{{0,0,0},{1,1,1},{0,0,0}}));
    }
}
