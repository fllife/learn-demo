package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/27 17:17
 */
public class leetcode98 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    TreeNode preRoot = new TreeNode(Integer.MAX_VALUE);
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        TreeNode left = root.left;
        if (left != null && left.val >= root.val) {
            return false;
        }
        TreeNode right = root.right;
        if (right != null && (right.val < root.val || right.val > preRoot.val)) {
            return false;
        }
        preRoot = root;
        return isValidBST(left) && isValidBST(right);
    }

    public static void main(String[] args) {

    }
}
