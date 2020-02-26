package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/10 11:24
 */
public class leetcode1 {
    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
       }
   }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      ListNode head, result;
      head = result = null;
      int flag = 0;
      while(l1 != null || l2 != null) {
          if (head == null) {
              result = new ListNode(0);
              head = result;
          } else {
              head.next = new ListNode(0);
              head =  head.next;
          }

          int l1Val = (l1 != null) ? l1.val : 0;
          int l2Val = (l2 != null) ? l2.val : 0;
          int sum = l1Val + l2Val + flag;
          if (sum >= 10) {
             sum = sum - 10;
             flag = 1;
          } else {
             flag = 0;
          }
          head.val = sum;
          l1 = (l1 != null) ?  l1.next : null;
          l2 = (l2 != null) ?  l2.next : null;
      }
      if (flag != 0) {
          head.next = new ListNode(flag);
      }
      return result;
    }
}
