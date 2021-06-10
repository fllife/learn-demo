package com.mxm.java.learn_demo.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Jack.Ma
 * @date: 2021/5/31
 **/
public class leetcode119 {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
             List list = new ArrayList();
             list.add(new Integer(1));
            return list;
        }
        List<Integer> lastRow = null;
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> row = new ArrayList<>(rowIndex+1);
            for (int j = 0; j <= i; j ++) {
                if (j == 0 || j == i) {
                    row.add(new Integer(1));
                } else {
                    row.add(lastRow.get(j - 1) + lastRow.get(j));
                }
            }
            lastRow = new ArrayList<>(row);
        }
        return lastRow;
    }

    public static void main(String[] args) {
        List<Integer> list = new leetcode119().getRow(3);
        System.out.println(list);

    }

}
