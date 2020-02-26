package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/17 10:25
 */
public class leetcode55 {
    int step = 0;
    public boolean backtrack(int[] nums, int index) {
        if (step >= nums.length - 1) {
            return true;
        }
        if (nums[index] == 0 && index != nums.length - 1) {
            return false;
        }

        for (int i = 1; i <= nums[index]; i++) {

            if (nums[0] - nums[i] != i  || i == nums.length -1) {
                step += i;
                boolean result = backtrack(nums, step);
                if (result) {
                    return true;
                }
                step -= i;
            }

        }
        return false;
    }
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        boolean maybeF = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && i != nums.length - 1) {
                maybeF = true;
                break;
            }
        }
        if (!maybeF) {
            return true;
        } else {
            return backtrack(nums,0);
        }
    }

    public static void main(String[] args) {
        System.out.println(new leetcode55().canJump(new int[]{2,0,0}));
    }
}
