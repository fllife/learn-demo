package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/11 14:39
 */
public class leetcode80 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int number = 1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                if (nums[i] == nums[i - 1]) {
                    number ++;
                    if (number > 2) {
                        for (int j = i; j < n - 1; j++) {
                            int temp = nums[j];
                            nums[j] = nums[j + 1];
                            nums[j + 1] = temp;
                        }
                        n --;
                        number --;
                        i -- ;
                    }
                } else {
                    number = 1;
                }
            }

        }
        return n;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1};
        int len = new leetcode80().removeDuplicates(nums);
        for (int i = 0; i < len; i++) {
            System.out.println(nums [i]);
        }
    }
}
