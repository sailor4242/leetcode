package com.dzavorin.solutions.strings;

public class ValidPalndrome2 {

    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, 1);
    }

    public boolean validPalindrome(String s, int lo, int hi, int k) {

        while (lo <= hi) {

            if (s.charAt(lo) != s.charAt(hi)) {
                if (k > 0) {
                    if (hi - lo == 1) {
                        return true;
                    } else {
                        return validPalindrome(s, lo + 1, hi, 0) || validPalindrome(s, lo, hi - 1, 0);
                    }
                } else {
                    return false;
                }
            }

            lo++;
            hi--;

        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidPalndrome2()
                .validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }


}
