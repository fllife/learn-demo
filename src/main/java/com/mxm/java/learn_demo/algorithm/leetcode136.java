package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/1
 **/
public class leetcode136 {
    public int singleNumber(int[] nums) {
        int startNumber = nums[0];
        for (int i = 1; i < nums.length; i++) {
            startNumber  = startNumber ^ nums[i];
        }
        return startNumber;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode136().singleNumber(new int[]{4,1,2,1,2}));
    }
}
