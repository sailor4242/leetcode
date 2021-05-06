package com.dzavorin.solutions.dynamic;

import java.util.Stack;

public class LongestValidParenthesis {

    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public int longestValidParentheses2(String s) {

        int res = 0;

        for (int lo = 0; lo < s.length(); lo++) {

            int leftCount = 0;
            if (s.charAt(lo) == '(') {
                leftCount++;
            } else {
                continue;
            }

            for (int hi = lo + 1; hi < s.length(); hi++) {


                if (s.charAt(hi) == '(') {
                    leftCount++;
                } else {
                    leftCount--;
                }

                if (leftCount == 0) {
                    res = Math.max(res, hi - lo + 1);
                } else if (leftCount < 0) {
                    break;
                }

            }
        }

        return res;
    }

}
