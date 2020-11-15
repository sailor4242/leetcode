package com.dzavorin.solutions.strings;

public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        char[] charS = s.toCharArray();
        char[] charP = p.toCharArray();
        int lenS = charS.length;
        int lenP = charP.length;
        int i = 0;
        int j = 0;
        int ts = -1;
        int tp = -1;
        while (i < lenS) {
            if (j < lenP && (charP[j] == '?' || charP[j] == charS[i])) {
                i++;
                j++;
            } else if (j < lenP && charP[j] == '*') {
                ts = i;
                tp = j++;
            } else if (ts == -1) {
                return false;
            } else {
                j = tp + 1;
                i = ++ts;
            }
        }
        while (j < lenP && charP[j] == '*') {
            j++;
        }
        return j == lenP;
    }


    ///////////// dp

    public boolean isMatch2(String s, String pa) {

        boolean[][] dp = new boolean[s.length() + 1][pa.length() + 1];

        dp[0][0] = true;

        int k = 0;
        while (k < pa.length() && pa.charAt(k) == '*') {
            dp[0][k + 1] = true;
            k++;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {

                char c = s.charAt(i - 1);
                char p = pa.charAt(j - 1);

                if (c == p || p == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][pa.length()];
    }

    public static void main(String[] args) {
//        System.out.println(new WildcardMatching().isMatch("aa", "a"));
//        System.out.println(new WildcardMatching().isMatch("a", "aa"));
//        System.out.println(new WildcardMatching().isMatch("aa", "*"));
//        System.out.println(new WildcardMatching().isMatch("acdcb", "a*c?b"));
//        System.out.println(new WildcardMatching().isMatch("kadceb", "*a*b"));
//        System.out.println(new WildcardMatching().isMatch("adceb", "*a*b"));
        System.out.println(new WildcardMatching().isMatch("abcabczzzde", "*abc???de*"));
    }

}
