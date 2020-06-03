package com.dzavorin.solutions.dynamic;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {

        Arrays.sort(pairs, Comparator.comparing(c -> c[0]));

        int[] dp = new int[pairs.length];

        Arrays.fill(dp, 1);

        for (int i = 0; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {

                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
        }

        return Arrays.stream(dp).max().getAsInt();

    }

    public int findLongestChainGreedy(int[][] pairs) {

        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        int cur = Integer.MIN_VALUE, ans = 0;
        for (int[] pair : pairs)
            if (cur < pair[0]) {
                cur = pair[1];
                ans++;
            }
        return ans;
    }
}
