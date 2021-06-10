package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/9
 **/
public class leetcode1310 {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
           int start = queries[i][0];
           int end = queries[i][1];
           result[i] = arr[start];
           for (int j = start + 1; j <= end; j++) {
               result[i] ^= arr[j];
           }
        }
        return result;
    }
}
