package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/12 10:40
 */
public class leetcode82 {

      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
     }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int pre = Integer.MAX_VALUE;
        ListNode dummy = new ListNode(Integer.MAX_VALUE);
        ListNode first = dummy;
        ListNode end = head;
        while(end != null) {
            if (pre != end.val) {
                if(end.next == null || end.next.val != end.val) {
                   first.next = end;
                   first = end;
                }
            }
            pre = end.val;
            end = end.next;
        }
        first.next = end;
        return dummy.next;
    }

    public static void main(String[] args) {
        leetcode82 leetcode82 = new leetcode82();
        ListNode head = leetcode82.new ListNode(-1);
        head.next = leetcode82.new ListNode(0);
        head.next.next = leetcode82.new ListNode(0);
        head.next.next.next = leetcode82.new ListNode(4);
        head.next.next.next.next = leetcode82.new ListNode(4);
        /*head.next.next.next.next.next = leetcode82.new ListNode(1);
        head.next.next.next.next.next.next = leetcode82.new ListNode(1);*/
        ListNode result = leetcode82.deleteDuplicates(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
