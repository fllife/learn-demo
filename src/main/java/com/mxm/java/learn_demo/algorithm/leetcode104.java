package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/5/28
 **/
public class leetcode104 {
    public class TreeNode {
        int val;
        TreeNode left;
      TreeNode right;
        TreeNode() {
        }
        TreeNode(int val) {
            this.val = val;
        }
        TreeNode(int val,TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));
    }

    public static void main(String[] args) {
       TreeNode val0 = new leetcode104().new TreeNode(0, null, null);
       TreeNode val3 = new leetcode104().new TreeNode(4, val0, null);
       TreeNode val4 = new leetcode104().new TreeNode(3, null, null);
       TreeNode val2 = new leetcode104().new TreeNode(2, val3, null);
       System.out.println(new leetcode104().maxDepth(val2));
    }

}
