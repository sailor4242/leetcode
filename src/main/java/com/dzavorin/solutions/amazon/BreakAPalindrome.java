package com.dzavorin.solutions.amazon;

public class BreakAPalindrome {

    public String breakPalindrome(String palindrome) {
        if (palindrome.length() <= 1) return "";

        char[] arr = palindrome.toCharArray();
        for (int i = 0; i < arr.length / 2; i++) {
            char ch = arr[i];
            if (ch != 'a') {
                arr[i] = 'a';
                return new String(arr);
            }
        }

        if (arr[arr.length - 1] == 'a') {
            arr[arr.length - 1] = 'b';
            return new String(arr);
        }

        return "";
    }

}
