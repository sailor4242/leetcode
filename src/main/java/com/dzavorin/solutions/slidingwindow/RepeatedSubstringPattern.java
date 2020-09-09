package com.dzavorin.solutions.slidingwindow;

import java.util.regex.Pattern;

public class RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {

        if (s.length() == 1) return false;

        for (int i = 0; i < s.length() / 2; i++) {
            if (checkNext(s, 0, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkNext(String s, int l, int h) {
        int begin = l;

        for (int i = h + 1; i < s.length(); i++) {
            if (s.charAt(begin) != s.charAt(i)) {
                return false;
            }
            begin++;
            if (begin > h) {
                begin = l;
            }

        }
        return begin == l;
    }

    public boolean repeatedSubstringPattern5(String str) {
        int n = str.length();

        for (int i = n / 2; i > 0; i--) {
            if (n % i == 0) {
                int left = 0;
                int right = i;

                while (right < n && str.charAt(left) == str.charAt(right)) {
                    left++;
                    right++;
                }

                if (right == n) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        Pattern pat = Pattern.compile("^(.+)\\1+$");
        return pat.matcher(s).matches();
    }

    public boolean repeatedSubstringPattern3(String s) {
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }

    public boolean repeatedSubstringPattern4(String s) {
        return ((s + s).indexOf(s, 1) != s.length());
    }

    // Knuth-Morris-Prat
    public void computeLPS(String s, int[] lps) {
        int n = s.length();
        lps[0] = 0;
        int j = 0, i = 1;
        while (i < n) {
            if (s.charAt(i) == s.charAt(j)) {
                lps[i++] = ++j;
            } else {
                if (j > 0) {
                    j = lps[j - 1];
                } else {
                    j = 0;
                    i++;
                }
            }
        }
    }

    public boolean repeatedSubstringPattern6(String s) {
        int n = s.length();
        int[] lps = new int[n];
        computeLPS(s, lps);
        return lps[n - 1] > 0 && ((n % (n - lps[n - 1])) == 0);
    }

    public static void main(String[] args) {
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abab"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("aba"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("a"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("aa"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("aaa"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abcabcabcabc"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abacababacab"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("ababab"));
        System.out.println(new RepeatedSubstringPattern().repeatedSubstringPattern("abaabaa"));
    }
}
