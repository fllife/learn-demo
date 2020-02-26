package com.mxm.java.learn_demo.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maxianming
 * @date 2019/9/17 16:11
 */
public class leetcode17 {
    Map<String, String> map = new HashMap(){{
        put("2","abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
       if (digits.length() < 1) {
           return new ArrayList<String> ();
       }
       if (digits.length() == 1) {
           List<String> result = new ArrayList<>();
           String str = map.get(digits);
           for (int i = 0; i < str.length(); i++ ) {
               result.add(str.charAt(i)+ "");
           }
           return result;
       }
       int mid = digits.length() / 2;
       List<String> left = letterCombinations(digits.substring(0, mid));
       List<String> right = letterCombinations(digits.substring(mid, digits.length()));
       return combinations(left, right);
    }

    private List<String> combinations(List<String> lefts, List<String> rights) {
        List<String> result = new ArrayList<>(lefts.size() * rights.size());
        for (String left : lefts) {
            for (String right: rights) {
                result.add(left + right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> result = new leetcode17().letterCombinations("");
        System.out.println(result);
    }
}
