package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/5/27
 **/
public class leetcode101_out_example {

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
    public boolean isSymmetric(TreeNode root) {
         long sum = treeRecursion(0, root);
         return sum == 0;
    }

    private int treeRecursion(int sum, TreeNode root) {
        if (root == null) {
            return sum;
        }
        if (root.left != null) {
            sum = treeRecursion( sum + root.left.val + 1, root.left);
        }
        if (root.right != null) {
            sum = treeRecursion( sum - root.right.val - 1, root.right);
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode val0 = new leetcode101_out_example().new TreeNode(0, null, null);
        TreeNode val3 = new leetcode101_out_example().new TreeNode(4, null, null);
        TreeNode val4 = new leetcode101_out_example().new TreeNode(3, null, null);
        TreeNode val2 = new leetcode101_out_example().new TreeNode(2, val0, val0);
        System.out.println(new leetcode101_out_example().isSymmetric(val2));

    }
}
