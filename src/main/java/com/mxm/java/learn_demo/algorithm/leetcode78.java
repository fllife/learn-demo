package com.mxm.java.learn_demo.algorithm;
import java.util.Arrays;
import	java.util.LinkedList;

import java.util.List;

/**
 * @author maxianming
 * @date 2019/11/6 15:21
 */
public class leetcode78 {

    List<List<Integer>> output = new LinkedList<>();
    private int n;
    public List<List<Integer>> subsets(int[] nums) {
        this.n = nums.length;
        Arrays.sort(nums);
        backtrack(0, nums, new LinkedList<>());
        return output;
    }

    public void backtrack(int index, int[] num, LinkedList<Integer> curr) {
        output.add(new LinkedList<>(curr));

        for (int i = index; i < this.n; i++) {
            curr.add(num[i]);
            backtrack(i + 1, num, curr);
            curr.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(new leetcode78().subsets(new int[]{1,2,3, 4}));
    }
}
