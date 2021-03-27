package com.dzavorin.solutions.strings;

public class BreakAPalindrome {

    public String breakPalindrome(String palindrome) {
        int len = palindrome.length();
        if (len == 0 || (len == 1 && palindrome.charAt(0) == 'a')) {
            return "";
        }

        char[] chars = palindrome.toCharArray();
        for (int i = 0; i < len; i++) {
            if (len % 2 != 0 && i == len / 2) continue;

            char ch = palindrome.charAt(i);
            if (ch > 'a') {
                chars[i] = 'a';
                return new String(chars);
            }
        }

        for (int i = len - 1; i >= 0; i--) {
            char ch = palindrome.charAt(i);
            if (ch < 'b') {
                chars[i] = 'b';
                return new String(chars);
            }
        }

        return "";
    }

}
