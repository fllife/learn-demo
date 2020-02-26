package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/19 10:10
 */
public class leetcode27 {
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return nums.length;
        }
        int length = 0;
        for (int i = 0; i < nums.length ; i++) {
            if(nums[i] != val) {
                nums [length] = nums [i];
                length ++ ;
            }
        }
        return length ;
    }
}
