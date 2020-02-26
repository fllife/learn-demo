package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/5 16:48
 */
public class leetcode74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        if (n == 0) {
            return false;
        }
        int m = matrix[0].length;
        if (m == 0) {
            return false;
        }
        int rowLow = 0;
        int rowHigh = n - 1;
        while(rowLow <= rowHigh) {
            int rowMid = (rowLow + rowHigh) / 2;
            if (matrix[rowMid][0] < target) {
                rowLow = rowMid + 1;
            } else if (matrix[rowMid][0] == target) {
                return true;
            } else {
                rowHigh = rowMid - 1;
            }
        }

        int rowN = rowLow > 0 ? rowLow - 1 : rowLow;


        int colLow = 0;
        int colHigh = m - 1;
        while(colLow <= colHigh) {
            int colMid = (colLow + colHigh) / 2;
            if (matrix[rowN][colMid] < target) {
                colLow = colMid + 1;
            } else if (matrix[rowN][colMid] == target) {
                return true;
            } else {
                colHigh = colMid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode74().searchMatrix(new int[][]{{1,   3,  5,  7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 17));
    }
}
