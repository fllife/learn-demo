package com.mxm.java.learn_demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maxianming
 * @date 2019/9/4 14:19
 */
public class leetcode2 {

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap(nums.length);
        for (int i = 0; i < nums.length; i ++) {
            int search = target - nums[i];
            if (map.containsKey(search)) {
                return new int[] {i, map.get(search)};
            } else {
                map.put(nums[i], i);
            }
        }
        throw new IllegalArgumentException("not exist int in nums");

    }
    public static void main(String[] args) {
        twoSum(new int[]{2, 7, 11, 15}, 9);
    }
}
