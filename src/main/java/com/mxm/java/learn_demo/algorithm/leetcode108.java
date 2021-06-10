package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/5/28
 **/
public class leetcode108 {
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

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        int mid = nums.length / 2;
        int[] leftNums = null;
        if (mid != 0) {
            leftNums = new int[mid];
            System.arraycopy(nums, 0, leftNums, 0, mid);
        }

        int[] rightNums = null;
        if (mid != nums.length - 1) {
            rightNums = new int[nums.length - mid - 1];
            System.arraycopy(nums, mid+1, rightNums, 0, nums.length - mid - 1);
        }
        return new TreeNode(nums[mid], sortedArrayToBST(leftNums), sortedArrayToBST(rightNums));

    }

    public static void main(String[] args) {
        int[] input = new int[] {1,3};
        TreeNode result = new leetcode108().sortedArrayToBST(input);
        printTree(result);
    }

    public static void printTree(TreeNode root) {
        if (root != null) {
            System.out.println(root.val + "");
            printTree(root.left);
            printTree(root.right);
        }
    }
}
