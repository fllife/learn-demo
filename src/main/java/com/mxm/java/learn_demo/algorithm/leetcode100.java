package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/27 16:31
 */
public class leetcode100 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        } else {
            boolean leftResult = isSameTree(p.left, q.left);
            if (!leftResult) {
                return false;
            } else {
                boolean rightResult = isSameTree(p.right, q.right);
                if (!rightResult) {
                    return false;
                } else {
                    return true;
                }
            }

        }
    }

    public static void main(String[] args) {
        TreeNode p = new leetcode100().new TreeNode(10);
        p.left =  new leetcode100().new TreeNode(5);
        p.right =  new leetcode100().new TreeNode(15);

        TreeNode q = new leetcode100().new TreeNode(10);
        q.left =  new leetcode100().new TreeNode(5);
        q.right = null;
        q.left.left = null;
        q.left.right =  new leetcode100().new TreeNode(15);

        System.out.println(new leetcode100().isSameTree(p, q));
    }
}
