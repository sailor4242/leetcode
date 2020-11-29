package com.dzavorin.solutions.dynamic;

import java.util.Arrays;
import java.util.HashSet;

public class NumberAtMostNGivenDigitSet {

    //////


    public int atMostNGivenDigitSet(String[] digits, int n) {

        String s = String.valueOf(n);
        int len = s.length();
        int prev = 1;
        int total = 0;

        for (int i = len - 1; i >= 0; i--) {
            int num = s.charAt(i) - '0';
            int numbers = 0;

            for (String str : digits) {
                int digit = str.charAt(0) - '0';
                if (digit < num) {
                    numbers += Math.pow(digits.length, len - i - 1);
                } else if (digit == num) {
                    numbers += prev;
                }
            }

            prev = numbers;
            total = numbers;
        }

        for (int i = 1; i < len; i++) {
            total += Math.pow(digits.length, i);
        }

        return total;
    }


    ///////////


    public int atMostNGivenDigitSet4(String[] D, int N) {
        String S = String.valueOf(N);
        int K = S.length();
        int[] dp = new int[K + 1];
        dp[K] = 1;

        for (int i = K - 1; i >= 0; --i) {
            int Si = S.charAt(i) - '0';
            for (String d : D) {
                if (Integer.parseInt(d) < Si)
                    dp[i] += Math.pow(D.length, K - i - 1);
                else if (Integer.parseInt(d) == Si)
                    dp[i] += dp[i + 1];
            }
        }

        for (int i = 1; i < K; ++i)
            dp[0] += Math.pow(D.length, i);

        return dp[0];
    }


    ////////


    public int atMostNGivenDigitSet2(String[] digits, int n) {
        int res = 0;
        String nStr = Integer.toString(n);

        for (int i = 1; i < nStr.length(); i++) {
            res += Math.pow(digits.length, i);
        }
        res += calcSameLength(0, digits, nStr);
        return res;
    }

    private int calcSameLength(int index, String[] digits, String nStr) {
        int res = 0;
        if (index == nStr.length() - 1) {
            for (String digit : digits) {
                if (digit.charAt(0) <= nStr.charAt(index)) {
                    res++;
                }
            }
            return res;
        }
        for (String digit : digits) {
            if (nStr.charAt(index) > digit.charAt(0)) {
                res += Math.pow(digits.length, nStr.length() - index - 1);
            } else if (nStr.charAt(index) == digit.charAt(0)) {
                res += calcSameLength(index + 1, digits, nStr);
            }
        }
        return res;
    }


    //// top down dp TLE

    int res = 0;

    public int atMostNGivenDigitSet3(String[] digits, int n) {

        Arrays.sort(digits);
        dfs(digits, n, new StringBuilder(), new HashSet<>());
        return res;
    }

    public boolean dfs(String[] digits, int n, StringBuilder sb, HashSet<String> memo) {
        if (sb.length() >= 11) return true;
        String cur = sb.toString();
        if (memo.contains(cur)) return true;
        if (sb.length() != 0) {
            long k = Long.parseLong(cur);
            if (k > n || k > 1000000000) {
                return true;
            } else if (k <= n) {
                res++;
            }
        }

        memo.add(cur);

        for (int i = 0; i < digits.length; i++) {
            sb.append(digits[i]);
            if (dfs(digits, n, sb, memo)) {
                sb.deleteCharAt(sb.length() - 1);
                break;
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new NumberAtMostNGivenDigitSet()
                .atMostNGivenDigitSet(new String[]{"1", "2", "3", "4", "5", "6", "7", "8"}, 8363065)); // 2221640

        System.out.println(new NumberAtMostNGivenDigitSet()
                .atMostNGivenDigitSet(new String[]{"3", "5"}, 4)); // 1
    }
}
