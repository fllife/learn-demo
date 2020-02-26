package com.mxm.java.learn_demo.algorithm;

/**
 * @author maxianming
 * @date 2019/9/20 15:31
 */
public class leetcode38 {
    public String countAndSay(int n) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= n; i ++) {
            if (stringBuffer.length() == 0) {
                stringBuffer.append("1");
            } else {
                for (int j = 0; j < stringBuffer.length(); j += 2) {
                    if (j + 1 < stringBuffer.length() && (stringBuffer.charAt(j) == '1' && stringBuffer.charAt(j + 1) == '1')  ) {
                        stringBuffer.setCharAt(j, '2');
                        stringBuffer.setCharAt(j+1, '1');
                    } else {
                        if (stringBuffer.charAt(j) == '1') {
                            stringBuffer.replace(j, j + 1, "11");
                        } else {
                            stringBuffer.replace(j, j + 1,  "12");
                        }
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(new leetcode38().countAndSay(5));
    }

}
