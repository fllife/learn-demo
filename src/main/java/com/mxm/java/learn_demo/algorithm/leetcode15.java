package com.mxm.java.learn_demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maxianming
 * @date 2019/9/17 9:40
 */
public class leetcode15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int k = i;
            if (nums[k] > 0 ) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int l = k + 1;
            int r = nums.length - 1;
            while (r > l) {
                int s = nums[k] + nums[l] + nums[r];
                if ( s < 0) {
                    while(l < r && nums[l] == nums[++l]);
                } else if (s > 0) {
                    while(l < r && nums[r] == nums[--r]);
                } else {
                    result.add( Arrays.asList(nums[k], nums[l], nums[r]));
                    while(l < r && nums[l] == nums[++l]);
                    while(l < r && nums[r] == nums[--r]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
