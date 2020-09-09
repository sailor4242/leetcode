package com.dzavorin.solutions.strings;

public class ConsecutiveCharacters {

    public int maxPower(String s) {
        if (s.isEmpty()) return 0;
        int res = 1;
        int c = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                c++;
                res = Math.max(res, c);
            } else {
                c = 1;
            }

        }

        return res;
    }

}
