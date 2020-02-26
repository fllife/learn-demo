package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/18 14:41
 */
public class leetcode21 {
     public class ListNode {
        int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode first = head;
         while (l1 != null || l2 != null) {
             if (l1 == null) {
                 first.next = l2;
                 break;
             } else if (l2 == null) {
                 first.next = l1;
                 break;
             } else {
                 int l1val = l1.val;
                 int l2val = l2.val;
                 if (l1val <= l2val) {
                     first.next = l1;
                     first = l1;
                     l1 = l1.next;
                 } else {
                     first.next = l2;
                     first = l2;
                     l2 = l2.next;
                 }
             }
         }
         return head.next;
    }

    public static void main(String[] args) {

    }
}
