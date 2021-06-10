package com.mxm.java.learn_demo.algorithm;

/**
 * @author: Jack.Ma
 * @date: 2021/6/2
 **/
public class leetcode160_no_idea {
     public class ListNode {
           int val;
           ListNode next;
           ListNode(int x) {
              val = x;
              next = null;
           }
     }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
         if (headA == null || headB == null) {
             return null;
         }
         ListNode head1 = headA;
         ListNode head2 = headB;
         while (head1 != head2) {
             if (head1 != null) {
                 head1 = head1.next;
             } else {
                 head1 = headB;
             }
             if (head2 != null) {
                 head2 = head2.next;
             } else {
                 head2 = headA;
             }
         }
         return head1;
    }

}
