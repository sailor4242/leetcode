package com.dzavorin.solutions.greedy;

import java.util.Arrays;

public class MaximumNumberOfConsecutiveValuesYouCanMake {

    public int getMaximumConsecutive(int[] c) {
        Arrays.sort(c);
        int max = 1;
        for (int i = 0; i < c.length && c[i] <= max; i++)
            max += c[i];
        return max;
    }

    public int getMaximumConsecutive2(int[] coins) {
        Arrays.sort(coins);
        if (coins[0] != 1) {
            return 1;
        }
        int n = coins.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int c = coins[i];
            if (c <= dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + c;
            } else {
                return dp[i - 1] + 1;
            }
        }
        return dp[n - 1] + 1;
    }
}
