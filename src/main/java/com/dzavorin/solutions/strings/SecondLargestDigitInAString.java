package com.dzavorin.solutions.strings;

public class SecondLargestDigitInAString {

    public int secondHighest(String s) {

        int max = -1;
        int secondMax = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int c = ch - '0';
                if (c > max) {
                    secondMax = max;
                    max = c;
                } else if (c > secondMax && c < max) {
                    secondMax = c;
                }
            }
        }

        return (int) secondMax;
    }

}
