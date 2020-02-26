package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/11/12 14:44
 */
public class leetcode86 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode end = dummy;
        ListNode start = dummy;
        while (end != null) {
            if (end.next != null && end.next.val < x) {
                ListNode tempEnd = end.next.next;
                ListNode tempStart = start.next;
                start.next = end.next;
                start = start.next;
                start.next = tempStart;
                end.next = tempEnd;
            }
            end = end.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        leetcode86 leetcode86 = new leetcode86();
        ListNode head = leetcode86.new ListNode(1);
        head.next = leetcode86.new ListNode(4);
        head.next.next = leetcode86.new ListNode(2);
        head.next.next.next = leetcode86.new ListNode(2);
 /*       head.next.next.next.next = leetcode86.new ListNode(2);*/
        /*head.next.next.next.next.next = leetcode86.new ListNode(2);*/
        /* head.next.next.next.next.next.next = leetcode82.new ListNode(1);*/
        ListNode result = leetcode86.partition(head,3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
