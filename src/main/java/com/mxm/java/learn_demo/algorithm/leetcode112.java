package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/5/28
 **/
public class leetcode112 {
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


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null ) {
            return false;
        }
        targetSum = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        } else {
            return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
        }
    }

    public static void main(String[] args) {

    }
}
