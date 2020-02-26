package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/18 18:07
 */
public class leetcode26 {
    public static int removeDuplicates(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int length = 1;
        for (int i = 1; i < nums.length ; i++) {
            if(nums[i] != nums [i-1]) {
                length ++;
                nums [length -1] = nums [i];
            }
        }
        return length;
    }

    public static void main(String[] args) {
       int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        int length = removeDuplicates(nums);
        for (int i = 0; i < length; i++) {
            System.out.println(nums[i]);
        }
    }
}
