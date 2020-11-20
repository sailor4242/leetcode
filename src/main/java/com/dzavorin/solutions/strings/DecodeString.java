package com.dzavorin.solutions.strings;

public class DecodeString {

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur >= '0' && cur <= '9') {
                num.append(s.charAt(i));
            } else if (cur == '[') {
                int end = findClosingBracket(s, i);
                String toAdd = decodeString(s.substring(i + 1, end));
                for (int k = 0; k < Integer.parseInt(num.toString()); k++) {
                    res.append(toAdd);
                }
                i = end;
                num = new StringBuilder();
            } else {
                res.append(cur);
            }
        }

        return res.toString();
    }

    int findClosingBracket(String s, int start) {
        int count = 1;
        while (start++ < s.length()) {
            if (s.charAt(start) == '[') {
                count++;
            } else if (s.charAt(start) == ']') {
                count--;
            }
            if (count == 0) {
                return start;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("13[a]2[bc]ef"));
        System.out.println(new DecodeString().decodeString("3[a2[c]]"));
    }
}
