package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/3
 **/
public class leetcode169_no_idea {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }
}
