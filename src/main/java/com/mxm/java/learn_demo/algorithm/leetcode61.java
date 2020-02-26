package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/18 9:49
 */
public class leetcode61 {
      public class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
      }
    public ListNode rotateRight(ListNode head, int k) {
          if (k == 0 || head == null) {
              return head;
          }
          ListNode tail = head;
          int size = 1;
          while(tail.next != null) {
              tail = tail.next;
              size ++ ;
          }
          int step = size - k % size;
          ListNode temp = head;
          for (int i = 1; i < step; i++) {
              temp = temp.next;
          }
          if (temp.next != null) {
              tail.next = head;
              head = temp.next;
              temp.next = null;
          }
          return head;

    }

    public static void main(String[] args) {
        leetcode61 leetcode61 = new leetcode61();
        ListNode h = leetcode61.new ListNode(0);
        h.next = leetcode61.new ListNode(1);
        h.next.next =  leetcode61.new ListNode(2);
        ListNode result = leetcode61.rotateRight(h, 3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
