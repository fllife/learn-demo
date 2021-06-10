package com.mxm.java.learn_demo.algorithm;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: Jack.Ma
 * @date: 2021/6/2
 **/
public class leetcode155 {
    Deque<Integer> mainStack = new LinkedList();
    Deque<Integer> slaveStack = new LinkedList();

    public void push(int val) {
        mainStack.push(val);
    }

    public void pop() {
        mainStack.pop();
    }

    public int top() {
        return mainStack.peek();
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        while (!mainStack.isEmpty()) {
            int element = mainStack.pop();
            min = element < min ? element : min;
            slaveStack.push(element);
        }
        while (!slaveStack.isEmpty()) {
            mainStack.push(slaveStack.pop());
        }
        return min;
    }

    public static void main(String[] args) {
        leetcode155 minStack = new leetcode155();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        System.out.println(minStack.getMin());

    }
}
