package com.mxm.java.learn_demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maxianming
 * @date 2019/9/18 9:38
 */
public class leetcode18 {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if(i>0&&nums[i]==nums[i-1])continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if(j>i+1&&nums[j]==nums[j-1])continue;

                if (nums[i + 1] == nums [i] && nums[j + 1] == nums[j])
                    break;

                int l = j + 1;
                int r = nums.length - 1;
                while(l < r) {
                    int sum = nums [i] + nums [j] + nums [l] + nums [r];
                    if (sum < target) {
                        while (l < r && nums [l] == nums[++l]);
                    } else if (sum > target) {
                        while(l < r && nums[r] == nums[--r]);
                    } else {
                        result.add(Arrays.asList(nums [i], nums[j], nums [l], nums [r]));
                        while (l < r && nums [l] == nums[++l]);
                        while (l < r && nums [r] == nums [--r]);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fourSum(new int[]{ 1, 0, -1, 0, -2, 2}, 0));
    }
}
