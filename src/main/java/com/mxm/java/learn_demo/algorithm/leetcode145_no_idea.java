package com.mxm.java.learn_demo.algorithm;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: Jack.Ma
 * @date: 2021/6/1
 **/
public class leetcode145_no_idea {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        TreeNode currNode = root;
        while (!stack.isEmpty()) {
            while (currNode.left != null || currNode.right != null) {
                if (currNode.left != null) {
                    stack.push(currNode.left);
                    currNode = currNode.left;
                } else {
                    stack.push(currNode.right);
                    currNode = currNode.right;
                }
            }
            result.add(currNode.val);
            stack.pop();
            currNode = stack.pop();
        }
        result.add(currNode.val);
        return result;
    }

    public static void main(String[] args) {

    }
}
