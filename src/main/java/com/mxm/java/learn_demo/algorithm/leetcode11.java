package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/12 16:16
 */
public class leetcode11 {
    public static  int maxArea(int[] height) {
        int maxArea = 0;
        int start = 0;
        int end = height.length - 1;
        while (end > start) {
            int area = (end - start)  * Math.min(height[start], height[end]);
            if (area > maxArea) {
                maxArea = area;
            }
           if (height [start] >= height [end]) {
                end -- ;
           } else {
                start ++ ;
           }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea( new int[]{1,8,6,2,5,4,8,3,7}));  ;
    }
}
