package com.mxm.java.learn_demo.algorithm;
import	java.util.LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maxianming
 * @date 2019/11/25 17:03
 */
public class leetcode90 {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        result.add(new ArrayList<>());
        if (nums == null || nums.length <= 0) {
            return result;
        }
        Arrays.sort(nums);
        backtrack(nums, 0, new LinkedList<Integer> ());
        return result;
    }

    private void backtrack(int[] nums, int k, LinkedList<Integer> lists) {
        if (lists.size() > 0) {
            result.add(new ArrayList<>(lists));
        }
        for (int i = k ; i < nums.length; i ++) {
            if (i > k && nums[i] == nums[i - 1]) {
                continue;
            }
            lists.add(nums[i]);
            backtrack(nums, i + 1, lists);
            lists.removeLast();

        }
    }

    public static void main(String[] args) {
        System.out.println(new leetcode90().subsetsWithDup(new int[]{1,2,2}));
    }
}
