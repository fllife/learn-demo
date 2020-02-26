package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/18 16:46
 */
public class leetcode24 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy.next;

        while (first != null && second != null) {
            if (second.next == null) break;
            first.next = second.next;
            second.next = second.next.next;
            first.next.next = second;

            first = second;
            second = second.next;
        }

        return dummy.next;

    }

}
