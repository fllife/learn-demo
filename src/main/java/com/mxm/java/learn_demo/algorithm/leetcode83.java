package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/12 14:11
 */
public class leetcode83 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        ListNode slow = dummy;
        ListNode fast = head;
        while(fast != null) {
            if(fast.next == null || fast.val != fast.next.val) {
                slow.next = fast;
                slow = fast;
            }
            fast = fast.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        leetcode83 leetcode83 = new leetcode83();
        ListNode head = leetcode83.new ListNode(1);
        head.next = leetcode83.new ListNode(1);
        head.next.next = leetcode83.new ListNode(1);
        head.next.next.next = leetcode83.new ListNode(1);
        head.next.next.next.next = leetcode83.new ListNode(1);
        /*head.next.next.next.next.next = leetcode82.new ListNode(1);
        head.next.next.next.next.next.next = leetcode82.new ListNode(1);*/
        ListNode result = leetcode83.deleteDuplicates(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
