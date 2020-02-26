package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/19 15:09
 */
public class leetcode31 {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == 0) {
                descendAndSwap(nums, -1, 0);
                return;
            } else if (nums[i - 1] < nums[i]) {
                descendAndSwap(nums, i-1, i);
                return;
            }
        }
    }

    private void descendAndSwap(int[] nums, int index, int l) {
        int left = l;
        int right = nums.length - 1;
        while (right >= left) {
            if (index >= 0 && nums[right] > nums[index]) {
                int temp = nums[index];
                nums[index] = nums[right];
                nums[right] = temp;
                index = -1;
            }

            int tempL = nums[left];
            nums [left] = nums [right];
            nums [right] = tempL;
            left++;
            right--;
        }
        if (index != -1) {
            int temp = nums[index];
            nums[index] = nums[nums.length -1];
            nums[nums.length -1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,1};
        new leetcode31().nextPermutation(nums);
        for(int i = 0; i < nums.length; i ++) {
            System.out.println(nums[i]);
        }
    }
}
