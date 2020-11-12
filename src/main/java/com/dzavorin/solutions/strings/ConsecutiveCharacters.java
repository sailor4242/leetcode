package com.dzavorin.solutions.strings;

public class ConsecutiveCharacters {

    public int maxPower(String s) {
        if (s == null || s.isEmpty()) return 0;

        int res = 1;
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 1;
            }

        }

        return res;
    }

}
