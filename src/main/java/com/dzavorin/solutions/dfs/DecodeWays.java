package com.dzavorin.solutions.dfs;

public class DecodeWays {

    public int numDecodings(String s) {
        return dfs(s, 0, new Integer[s.length()]);
    }

    private int dfs(String s, int i, Integer[] memo) {
        if (i == s.length()) return 1;
        if (i > s.length()) return 0;

        if (memo[i] != null) return memo[i];

        char cur = s.charAt(i);

        if (cur == '0') return 0;

        int res = 0;

        res += dfs(s, i + 1, memo);

        if ((cur == '1' || cur == '2') && (i + 1 < s.length() && (cur - '0') * 10 + (s.charAt(i + 1) - '0') <= 26)) {
            res += dfs(s, i + 2, memo);
        }

        return memo[i] = res;
    }

    public static void main(String[] args) {
        System.out.println(new DecodeWays().numDecodings("12"));
        System.out.println(new DecodeWays().numDecodings("111111111111111111111111111111111111111111111"));
    }
}
