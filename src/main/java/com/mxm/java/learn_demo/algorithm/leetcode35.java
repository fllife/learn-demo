package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/20 13:46
 */
public class leetcode35 {
    public int searchInsert(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int privot = (left + right) / 2;
            if (nums[privot] == target) {
                return privot;
            }

            if (left == right) {
                if (nums[left] > target) {
                    return left;
                } else {
                    return left + 1;
                }
            }

            if (nums[privot] > target) {
                right = privot;
            } else {
                left = privot + 1;
            }
        }
       return 0;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode35().searchInsert(new int[]{3,5,7,9,10
        }, 8));
    }
}
