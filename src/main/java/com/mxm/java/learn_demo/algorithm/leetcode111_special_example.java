package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/5/28
 **/
public class leetcode111_special_example {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return 1 + minDepth(root.right);
        } else if (root.right == null) {
            return 1 + minDepth(root.left);
        }
        return Math.min(1 + minDepth(root.left), 1 + minDepth(root.right));
    }

    public static void main(String[] args) {

    }
}
