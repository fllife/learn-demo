package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/26 17:37
 */
public class leetcode92 {

     public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

    public ListNode reverseBetween(ListNode head, int m, int n) {
         if (m == n) {
             return head;
         }
         ListNode dummy = new ListNode(0);
         dummy.next = head;
         head = dummy;

         int index = 0;
         while(head.next != null) {
             if (++index == m) {
                 break;
             } else {
                 head = head.next;
             }
         }
         ListNode slow = head.next;
         ListNode fast = slow.next;
         while (++index <= n && fast != null) {
             ListNode temp = fast.next;
             fast.next = slow;
             slow = fast;
             fast = temp;
         }

         head.next.next = fast;
         head.next = slow;
         return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new leetcode92().new ListNode(1);
        head.next = new leetcode92().new ListNode(2);
        head.next.next = new leetcode92().new ListNode(3);
        head.next.next.next = new leetcode92().new ListNode(4);
        head.next.next.next.next = new leetcode92().new ListNode(5);

        ListNode result = new leetcode92().reverseBetween(head, 1, 2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
