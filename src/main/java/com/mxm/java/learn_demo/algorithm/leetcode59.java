package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/17 15:57
 */
public class leetcode59 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int number = 0;
        int start = 0;
        while (number < n * n ) {
            for (int i = start, j = start; j < n - start && number < n * n; j++) {
                matrix [i] [j] = ++ number;
            }
            for (int j = n - 1 - start, i = 1+ start; i < n - start && number < n * n; i++) {
                matrix [i] [j] = ++ number;
            }
            for (int i = n - 1 -start, j = n - 2 - start; j >= start && number < n * n; j --) {
                matrix [i] [j] = ++ number;
            }
            for (int j = start, i = n - 2 - start; i > start && number < n * n; i --) {
                matrix [i] [j] = ++ number;
            }
            start ++ ;
        }
        return matrix;
    }

    public static void main(String[] args) {
        int [][] result = new leetcode59().generateMatrix(4);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result [i] [j] + " ");
            }
            System.out.println();
        }
    }
}
