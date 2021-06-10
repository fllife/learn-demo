package com.mxm.java.learn_demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Jack.Ma
 * @date: 2021/5/31
 **/
public class leetcode118 {
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>(0);
        }
        List<List<Integer>> result = new ArrayList<>(numRows);
        for (int i = 1; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>(i);
            for (int j = 0; j < i; j ++) {
                if (j == 0 || j == i - 1) {
                    row.add(new Integer(1));
                } else {
                    List<Integer> lastRow = result.get(i - 2);
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = new leetcode118().generate(4);
        for (List<Integer> list : result) {
            System.out.print(list.toString());
            System.out.println();
        }
    }
}
