package com.mxm.java.learn_demo.algorithm;
import	java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

/**
 * @author maxianming
 * @date 2019/11/27 11:09
 */
public class leetcode94  {
      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<> ();
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                result.add(pNode.val);
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
        return result;
    }
}
