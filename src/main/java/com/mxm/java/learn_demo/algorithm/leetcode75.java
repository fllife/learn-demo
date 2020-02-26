package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/6 9:54
 */
public class leetcode75 {
    public void sortColors(int[] nums) {
        if (nums.length > 1) {
            int start = 0;
            int end = nums.length - 1;
            for (int i = 0; i < nums.length && i >= start && i< end;) {
                if (nums[i] == 0) {
                    while(nums [start] == 0 && start < nums.length && start != i) {
                        start ++;
                    }
                    int temp = nums[i];
                    nums[i] = nums[start];
                    nums[start] = temp;

                } else if (nums[i] == 2) {
                    while(nums [end] == 2 && end > 0 && end != i) {
                        end --;
                    }
                    int temp = nums[i];
                    nums[i] = nums[end];
                    nums[end] = temp;
                }
                if (nums[i] == 1) {
                    i ++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{2,1,0};
        new leetcode75().sortColors(input);
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }
}
