package com.mxm.java.learn_demo.algorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author maxianming
 * @date 2019/10/16 9:53
 */
public class leetcode46 {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length <= 1){
            result.add(Arrays.asList(nums[0]));
            return result;
        }
        List<Integer> list = new ArrayList<> (nums.length);
        findPermute(nums, list);

       return result;
    }

    private void findPermute(int[] nums, List<Integer> list) {
        if (list.size() >= nums.length) {
            result.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (list.indexOf(nums [i]) == -1 || nums[list.indexOf(nums [i]) ] == nums[i]) {
                list.add(nums [i]);
                findPermute(nums, list);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new leetcode46().permute(new int[]{1,2,3}));
    }
}
