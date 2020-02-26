package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/10/18 16:54
 */
public class leetcode67 {
    public String addBinary(String a, String b) {
        StringBuffer result = new StringBuffer();
        int n = a.length() - 1;
        int m = b.length() - 1;
        int carry = 0;
        while (n >= 0 || m >= 0) {
            int i = n >= 0 ? a.charAt(n) - '0' : 0;
            int j = m >= 0 ? b.charAt(m) - '0' : 0;
            int sum = i + j + carry;
            if (sum > 1) {
                carry = 1;
                sum = sum - 2;
            } else {
                carry = 0;
            }
            result.append(sum);
            n --;
            m --;
        }
        if (carry == 1) {
            result.append(1);
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new leetcode67().addBinary(  "11", "1"));
    }
}
