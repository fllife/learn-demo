package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/18 13:34
 */
public class leetcode20 {
    public static boolean isValid(String s) {
        StringBuffer stack = new StringBuffer();
        if (s.length() == 0) {
            return true;
        } else if (s.length() == 1) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (stack.length() == 0 && (')' == s.charAt(i) || ']' == s.charAt(i) || '}' == s.charAt(i))) {
                return false;
            }
                if (')' == s.charAt(i)) {
                    if (stack.substring(stack.length() - 1,stack.length()).equals("(")) {
                        stack.delete(stack.length() - 1, stack.length());
                    } else {
                        return false;
                    }
                }
                else if (']' == s.charAt(i)) {
                    if (stack.substring(stack.length() - 1,stack.length()).equals("[")) {
                        stack.delete(stack.length() - 1, stack.length());
                    } else {
                        return false;
                    }
                }
                else if ('}' == s.charAt(i)) {
                    if (stack.substring(stack.length() - 1,stack.length()).equals("{")) {
                        stack.delete(stack.length() - 1, stack.length());
                    } else {
                        return false;
                    }
                }
                else {
                        stack.append(s.charAt(i));
                }
            }
        return stack.length() == 0;
        }


    public static void main(String[] args) {
        System.out.println(isValid( "([)]"));
    }
}
