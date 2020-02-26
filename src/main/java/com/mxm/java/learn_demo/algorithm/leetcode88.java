package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/12 16:49
 */
public class leetcode88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
      int i = 0;
      int j = 0;
      while (i < n && j < m) {
          if (nums2[i] < nums1 [j]) {
                  for (int k = m; k > j; k --) {
                      nums1[k] = nums1 [k - 1];
                  }
                  nums1[j] = nums2[i++];
                  m += 1;
          }
          j ++;
      }
      while (i < n) {
          nums1[j++] = nums2 [i++];
      }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,2,0,0,0};
        int[] nums2 = {-1,-2, -3};
        new leetcode88().merge(nums1, 3, nums2, 3);
        for (int i = 0; i < nums1.length; i++) {
            System.out.println(nums1 [i]);
        }
    }
}
