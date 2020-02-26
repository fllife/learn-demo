package com.mxm.java.learn_demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maxianming
 * @date 2019/11/27 14:42
 */
public class leetcode95 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0){
            return new ArrayList<>();
        }

        return  generateTree(1, n);
    }

    private List<TreeNode> generateTree(int m, int n) {
        List<TreeNode> list = new ArrayList<>();
        if (m > n) {
            list.add(null);
        }
        for (int i = m; i <= n; i ++){
            List<TreeNode> lefts = generateTree(m, i - 1);
            List<TreeNode> rights = generateTree(i+ 1, n);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }

            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new leetcode95().generateTrees(0));
    }
}
