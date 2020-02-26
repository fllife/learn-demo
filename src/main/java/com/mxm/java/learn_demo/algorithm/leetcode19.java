package com.mxm.java.learn_demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author maxianming
 * @date 2019/9/18 10:55
 */
public class leetcode19 {

   public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> map = new HashMap<> ();
        int index = -1;
        ListNode temp = head;
        while (temp != null) {
            map.put(++index, temp);
            temp = temp.next;
        }
        if (n == index + 1) {
            head = head.next;
        } else {
            ListNode preNode = map.get(index - n);
            ListNode postNode = map.get(index - n + 2);
            preNode.next = postNode;
        }
        return head;
    }
}
