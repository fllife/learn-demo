package com.mxm.java.learn_demo.algorithm;

import java.util.Stack;

/**
 * @author maxianming
 * @date 2019/10/18 15:10
 */
public class leetcode66 {
    public int[] plusOne(int[] digits) {
        Stack<Integer> stack = new Stack();
        int carry = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            if ( i == digits.length - 1) {
                digits [i] += 1;
                if (digits [i] > 9) {
                    carry = 1;
                    digits [i] = digits [i]  - 10;
                }
            } else {
                digits [i] += carry;
                if (digits [i] > 9) {
                    carry = 1;
                    digits [i] = digits [i]  - 10;
                } else {
                    carry = 0;
                }
            }
            stack.push(digits [i]);
        }
        if (carry > 0) {
            stack.push(carry);
        }
        int[] result = new int[stack.size()];
        for (int j = 0; j < result.length; j++) {
            result [j] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = new leetcode66().plusOne(new int[]{9,9,9});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result [i]);
        }

    }
}
