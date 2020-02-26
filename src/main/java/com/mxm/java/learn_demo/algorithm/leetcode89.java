package com.mxm.java.learn_demo.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * @author maxianming
 * @date 2019/11/22 13:35
 */
public class leetcode89  {
    List<Integer> result = new LinkedList<>();
    public List<Integer> grayCode(int n) {
        if (n <= 0) {
            result.add(0);
            return result;
        }
        backtrack("", 0, n);
        return result;
    }

    private void backtrack(String str, int i, int n) {
        if ( i == n) {
            result.add(Integer.valueOf(str, 2));
            return;
        }
        for (int j = 0; j <= 1; j++){
            str = str + j;
            backtrack(str, i + 1, n);
            str = str.substring(0, str.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new leetcode89().grayCode(2));
    }
}
