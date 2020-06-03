package com.dzavorin.solutions.dynamic;

public class PerfectSquares {

    public int numSquares(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 0;
        int top = (int)Math.sqrt(n);
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= top; j++) {
                int pow = (int) Math.pow(j, 2);
                if (pow <= i) {
                    dp[i] = Math.min(dp[i], dp[i - pow] + 1);
                }
            }

        }

        return dp[n];

    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(15));
        System.out.println(new PerfectSquares().numSquares(12));
        System.out.println(new PerfectSquares().numSquares(1));
        System.out.println(new PerfectSquares().numSquares(3));
        System.out.println(new PerfectSquares().numSquares(5));
        System.out.println(new PerfectSquares().numSquares(9));
    }
}
