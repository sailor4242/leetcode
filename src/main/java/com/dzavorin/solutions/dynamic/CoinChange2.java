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

    // not working
    public int change2(int amount, int[] coins) {

        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            int count = 0;
            for (int j = 0; j < coins.length; j++) {
                if (i % coins[j] == 0) {
                    count++;
                }
                if (i > coins[j] && i - coins[j] >= 0 && dp[i - coins[j]] != 0) {
                    count++;
                }
            }
            dp[i] = count;
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(new CoinChange2().change(5, new int[]{1, 2, 5}));
        System.out.println(new CoinChange2().change(2, new int[]{1, 2, 5}));
    }
}
