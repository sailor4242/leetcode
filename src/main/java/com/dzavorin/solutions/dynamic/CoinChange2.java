package com.dzavorin.solutions.dynamic;

public class CoinChange2 {

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int c : coins)
            for (int i = c; i <= amount; i++)
                dp[i] += dp[i - c];

        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange2().change(11, new int[]{1, 2, 5}));
        System.out.println(new CoinChange2().change(2, new int[]{1, 2, 5}));
    }
}
