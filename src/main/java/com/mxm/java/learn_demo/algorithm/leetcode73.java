package com.mxm.java.learn_demo.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author maxianming
 * @date 2019/11/5 15:39
 */
public class leetcode73 {
    public static void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    col.add(j);
                }
            }
        }

        for (int k = 0; k < matrix.length; k++) {
            for (int m = 0; m < matrix[k].length; m++) {
               if (row.contains(k) || col.contains(m)) {
                   matrix[k][m] = 0;
               }
            }
        }
    }

    public static void main(String[] args) {
     int[][] input = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        setZeroes(input);
        for (int[] row : input) {
            for(int ele : row) {
                System.out.print(ele + ",");
            }
            System.out.println();
        }
    }
}
