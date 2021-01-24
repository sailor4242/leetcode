package com.dzavorin.solutions.dynamic;

import java.util.LinkedList;

public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if (num.length() <= k) {
            return "0";
        }

        LinkedList<Character> stack = new LinkedList<>();
        // k keeps track of how many characters we can remove
        // if the previous character in stack is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && stack.getLast() > c && k > 0) {
                stack.removeLast();
                k--;
            }
            stack.add(c);
        }

        while (!stack.isEmpty() && k > 0) {
            stack.removeLast();
            k--;
        }

        // removing zeros at front
        // find the index of first non-zero digit
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            char c = stack.removeFirst();
            if (!(sb.length() == 0 && c == '0')) {
                sb.append(c);
            }
        }

        String res = sb.toString();
        return "".equals(res) ? "0" : res;
    }

    /// sb instead of stack

    public String removeKdigits3(String s, int k) {

        if (k >= s.length()) return "0";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char cur = s.charAt(i);

            while (sb.length() > 0 && s.charAt(i) < sb.charAt(sb.length() - 1) && k > 0) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }

            sb.append(s.charAt(i));

        }

        while (sb.length() > 0 && k > 0) {
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }

        int i = 0;
        while (sb.charAt(i) == '0' && sb.length() > 1) {
            sb.deleteCharAt(i);
        }

        return sb.toString();
    }

    ///

    public static String removeKdigits2(String num, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : num.toCharArray()) {
            while (k > 0 && sb.length() != 0 && sb.charAt(sb.length() - 1) > c) {
                sb.setLength(sb.length() - 1);
                k--;
            }
            if (sb.length() != 0 || c != '0') sb.append(c);  // Only append when it is not leading zero
        }
        if (k >= sb.length()) return "0";
        sb.setLength(sb.length() - k);  // use all remaining k
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new RemoveKDigits().removeKdigits("1432219", 3)); // 1219
//        System.out.println(new RemoveKDigits().removeKdigits("10200", 1)); // 200
//        System.out.println(new RemoveKDigits().removeKdigits("10", 2)); // 0
//        System.out.println(new RemoveKDigits().removeKdigits("9", 1)); // 0
        System.out.println(new RemoveKDigits().removeKdigits("112", 1)); // 0
    }
}
