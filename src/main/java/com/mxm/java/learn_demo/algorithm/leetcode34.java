package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/20 11:18
 */
public class leetcode34 {
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        if (nums.length == 0) {
            return result;
        } else if (result.length == 1) {
            if (target == nums [0])
                return new int[]{0, 0};
            return result;
        }

        int start = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int privot = (left + right) / 2;
            if (nums[privot] == target) {
                start = privot;
                right--;
            } else if (nums[privot] > target) {
                right = privot - 1;
            } else {
                left = privot + 1;
            }
        }
        if (start == -1) {
            return result;
        } else {
            result[0] = start;
            result[1] = nums.length - 1;
            for (int j = start; j < nums.length; j++) {
                if (nums[j] != target) {
                    result[1] = j - 1;
                    break;
                }
            }
            return result;
        }

    }

    public static void main(String[] args) {
        int[] result = new leetcode34().searchRange(new int [] {1,2,3}, 1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result [i]);
        }

    }

}
