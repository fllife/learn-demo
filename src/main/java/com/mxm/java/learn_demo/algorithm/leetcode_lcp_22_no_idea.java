package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/16
 **/
public class leetcode_lcp_22_no_idea {
    public int paintingPlan(int n, int k) {
        if (k == 0) {
            return 1;
        } else if (0 < k && k < n) {
            return 0;
        }
        return 0;
    }
}
