package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/16 13:59
 */
public class leetcode48 {
    public void rotate(int[][] matrix) {
        if (matrix.length <= 1) {
            return;
        }

        int n = matrix.length;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix [i].length; j++){
                int k = j;
                int m = n - i - 1;
                int swap = matrix[i][j];
                while (k != i && m != j) {
                    int temp = matrix[k][m];
                    matrix[k][m] = swap;
                    swap = temp;
                    k = m;
                    m = n - k -1;
                }
                matrix[i][j] =  matrix [k] [m];

            }
        }
    }
}
