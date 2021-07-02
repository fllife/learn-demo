package com.mxm.java.learn_demo.algorithm;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: Jack.Ma
 * @date: 2021/6/17
 **/
public class leetcode_offer_31  {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length == 0) {
            return true;
        }
        Queue<Integer> queue = new LinkedBlockingQueue<>();
        for (int pop : popped) {
            queue.add(pop);
        }
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!queue.isEmpty() && queue.peek().equals(stack.peek())) {
                queue.poll();
                stack.poll();
            }
        }
        return queue.isEmpty();
    }
}
