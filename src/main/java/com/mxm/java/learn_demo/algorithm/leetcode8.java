package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/12 13:30
 */
public class leetcode8 {
    public static int myAtoi(String str) {
      int result = 0;
      boolean positive = true;
      boolean first = false;
      for (int i = 0; i < str.length(); i++) {
          if (' ' == str.charAt(i) && !first) {
              continue;
          } else {
              if (('-' == str.charAt(i) && !first)) {
                  first = true;
                  positive = false;
                  continue;
              }
              if (('+' == str.charAt(i) && !first)) {
                  first = true;
                  continue;
              }
              if (str.charAt(i) <= 57 && str.charAt(i) >= 48) {
                  first = true;
                  int num = str.charAt(i) - 48;
                  if ((result > Integer.MAX_VALUE / 10 || result == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10 )) {
                       return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                  }
                  result = result * 10 + num ;
              } else {
                  if (result > 0 && !positive) {
                      return - result;
                  }
                  return result;
              }
          }
      }
      return positive ? result : - result;
    }

    public static void main(String[] args) {

        System.out.println(myAtoi("2147483648"));
    }
}
