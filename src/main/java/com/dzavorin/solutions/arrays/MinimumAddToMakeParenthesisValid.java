package com.dzavorin.solutions.arrays;

public class MinimumAddToMakeParenthesisValid {

    public int minAddToMakeValid(String S) {

        int res = 0;
        int state = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == '(') {
                state++;
            } else {
                state--;
            }

            if (state < 0) {
                res++;
                state = 0;
            }
        }
        res += state;
        return res;
    }

}
