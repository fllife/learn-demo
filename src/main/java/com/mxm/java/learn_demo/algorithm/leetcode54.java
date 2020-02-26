package com.mxm.java.learn_demo.algorithm;
import	java.util.ArrayList;

import java.util.List;

/**
 * @author maxianming
 * @date 2019/10/17 9:19
 */
public class leetcode54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<> ();
        int m = matrix.length;
        if (m <= 0) {
            return result;
        }
        int n = matrix [0].length;
        int number = 0;
        int start = 0;
        while (number < m * n && m * n >0) {
            for (int i = start, j = start; j < n - start && number < m * n; j++) {
                result.add(matrix [i] [j]);
                number ++ ;
            }
            for (int j = n - 1 - start, i = 1+ start; i < m - start && number < m * n; i++) {
                result.add(matrix [i] [j]);
                number ++ ;
            }
            for (int i = m - 1 -start, j = n - 2 - start; j >= start && number < m * n; j --) {
                result.add(matrix [i] [j]);
                number ++ ;
            }
            for (int j = start, i = m - 2 - start; i > start && number < m * n; i --) {
                result.add(matrix [i] [j]);
                number ++ ;
            }
            start ++ ;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode54().spiralOrder(new int[][]{}));
    }
}
