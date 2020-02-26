package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/27 13:58
 */
public class leetcode96 {
    public int numTrees(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] g = new int[n+1];
        g[0] = g[1] = 1;
        for (int i = 2; i <= n; i ++) {
            for (int j = 1; j <= i; j++) {
                g[i] += g[j - 1] * g[i - j];
            }
        }
        return g[n];
    }

    public static void main(String[] args) {
        System.out.println(new leetcode96().numTrees(3));
    }
}
