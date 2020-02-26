package com.mxm.java.learn_demo.algorithm;

import java.util.Stack;

/**
 * @author maxianming
 * @date 2019/11/5 14:10
 */
public class leetcode71 {

   public String simplifyPath(String path) {
       String[] paths =  path.split("/");
       Stack<String> stack = new Stack<String>();
       stack.push("/");
       for (String strPath : paths) {
           if (strPath.equals("/") || strPath.equals(".") || strPath.equals("")) {
               continue;
           } else if (strPath.equals("..")) {
               if (stack.size() > 1) {
                   stack.pop();
               }
               if (stack.size() > 1) {
                   stack.pop();
               }
           } else {
               stack.push(strPath);
               stack.push("/");
           }
       }
       StringBuilder result = new StringBuilder();
       for (Object  ele : stack.toArray()) {
           result.append(ele);
       }

       if (result.length() > 1) {
           return result.substring(0, result.length() - 1);
       } else {
           if (result.length() == 0) {
               return "/";
           } else {
               return result.toString();
           }
       }
    }

    public static void main(String[] args) {
        System.out.println(new leetcode71().simplifyPath("/a/../../b/../c//.//"));
    }
}
