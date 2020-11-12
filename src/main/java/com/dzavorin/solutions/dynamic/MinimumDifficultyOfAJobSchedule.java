package com.dzavorin.solutions.dynamic;

import java.util.HashMap;
import java.util.Map;

public class MinimumDifficultyOfAJobSchedule {

    /// top down
    public int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty.length < d) return -1;

        return minDifficulty(jobDifficulty, d, new HashMap<>(), 0);
    }

    public int minDifficulty(int[] jobs, int d, Map<String, Integer> memo, int j) {
        if (d == 0 && j == jobs.length) {
            return 0;
        } else if (d <= 0 || j >= jobs.length) {
            return Integer.MAX_VALUE;
        }

        String s = d + "_" + j;

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        int res = Integer.MAX_VALUE;
        int max = jobs[j];

        for (int i = j; i < jobs.length; i++) {

            max = Math.max(max, jobs[i]);
            int res2 = minDifficulty(jobs, d - 1, memo, i + 1);

            if (res2 != Integer.MAX_VALUE) {
                res = Math.min(res, max + res2);
            }

        }

        memo.put(s, res);

        return res;
    }

    //////////////// bottom up

    public int minDifficulty2(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) return -1;
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 1; i <= d; i++) {
            dp[n][i] = Integer.MAX_VALUE; // There're days unused.
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE; // No day left.
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= d; j++) {
                int min = Integer.MAX_VALUE;
                int max = 0;
                for (int k = i; k < n; k++) {
                    max = Math.max(max, jobDifficulty[k]);
                    if (dp[k + 1][j - 1] != Integer.MAX_VALUE) {
                        min = Math.min(min, max + dp[k + 1][j - 1]);
                    }
                }
                dp[i][j] = min;
            }
        }
        return dp[0][d] == Integer.MAX_VALUE ? -1 : dp[0][d];
    }

}
