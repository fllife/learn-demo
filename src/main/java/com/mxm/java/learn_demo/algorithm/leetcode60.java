package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/17 16:10
 */
public class leetcode60 {
    public String getPermutation(int n, int k) {
        int[] result = new int[n];
        for (int i = 0; i < n; i ++){
           result [i] = i + 1;
        }

        if (k <= 1) {
            return toString(result);
        }
        int number = 1;
        int j = n - 1;
        while (number < k) {
            if ( result[j]  < result[j - 1]) {
                j --;
            } else {
                for (int i = n - 1; i > 0; i --) {
                    if (result[i] > result[j - 1]) {
                        int temp = result[j - 1];
                        result[j - 1] = result[i];
                        result[i] = temp;
                        break;
                    }
                }
                for (int m = j, q = n-1;  m < q; m ++, q--) {
                    int temp = result[m];
                    result[m] = result[q];
                    result[q] = temp;
                    j = n - 1;
                }
                number ++;
            }
        }
        return toString(result);
    }

    private String toString(int[] result) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            str.append(result[i]);
        }
        return str.toString();
    }

    public static void main(String[] args) {
        System.out.println(new leetcode60().getPermutation(5, 120));
    }
}
