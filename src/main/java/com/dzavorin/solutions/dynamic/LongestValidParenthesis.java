package com.dzavorin.solutions.dynamic;

public class LongestValidParenthesis {

    public int longestValidParentheses(String s) {

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
