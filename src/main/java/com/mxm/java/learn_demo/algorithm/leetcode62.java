package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/18 11:11
 */
public class leetcode62 {
    public int uniquePaths(int m, int n) {
        if (m <= 1 || n <= 1) {
            return 1;
        }
        int[] result = new int[m];
        for (int i = 0; i < m; i++ ) {
            result [i] = 1;
        }

        int index = 0;
        for (int j = 0; j < n - 1; j++) {
            while ( ++index < m) {
                result[index] = result [index] + result[index - 1];
            }
            index = 0;
        }
        return result[m - 1];
    }

    public static void main(String[] args) {
        System.out.println(new leetcode62().uniquePaths(3,3));
    }
}
