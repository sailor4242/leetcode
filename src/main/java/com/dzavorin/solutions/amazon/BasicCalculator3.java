package com.dzavorin.solutions.amazon;

public class BasicCalculator3 {

    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int res = 0;
        int cur = 0;
        int last = 0;
        char operator = '+';
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                cur = cur * 10 + (ch - '0');
            }

            if (ch == '(') {
                int closingIdx = findClosingBracket(s, i + 1);
                cur = calculate(s.substring(i + 1, closingIdx));
                i = closingIdx;
            } else if (ch == ')') {
                break;
            }

            if (!Character.isDigit(ch) && !Character.isWhitespace(ch) || i == s.length() - 1) {
                if (operator == '+') {
                    res += last;
                    last = cur;
                } else if (operator == '-') {
                    res += last;
                    last = -cur;
                } else if (operator == '*') {
                    last = last * cur;
                } else if (operator == '/') {
                    last = last / cur;
                }

                operator = ch;
                cur = 0;
            }
        }

        res += last;
        return res;
    }

    public int findClosingBracket(String s, int j) {
        int count = 1;
        for (int i = j; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                count--;
            } else if (s.charAt(i) == '(') {
                count++;
            }

            if (count == 0) {
                return i;
            }
        }

        return -1;
    }

}
