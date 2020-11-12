package com.dzavorin.solutions.dynamic.partition;

import java.util.Arrays;

public class LargestSumOfAverages {

    public double largestSumOfAverages(int[] A, int K) {

        int N = A.length;
        double[] P = new double[N + 1];
        for (int i = 0; i < N; ++i) {
            P[i + 1] = P[i] + A[i];
        }

        double[] dp = new double[N];
        for (int i = 0; i < N; ++i) {
            dp[i] = (P[N] - P[i]) / (N - i);
        }

        for (int k = 0; k < K - 1; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = i + 1; j < N; ++j) {
                    dp[i] = Math.max(dp[i], (P[j] - P[i]) / (j - i) + dp[j]);
                }
            }
        }

        return dp[0];
    }

    public double largestSumOfAverages2(int[] A, int K) {
        double[] prefix = new double[A.length + 1];

        double[][] dp = new double[prefix.length][K + 1];
        for (double[] row : dp) {
            Arrays.fill(row, -1);
        }
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + A[i - 1];
        }

        return dfs(prefix, 1, K, dp);
    }

    double dfs(double[] prefix, int start, int K, double[][] dp) {
        if (start >= prefix.length) return 0;
        // if K == 1 because we have to use all of the elements, just return the average of the rest of the array
        if (K == 1) {
            return (prefix[prefix.length - 1] - prefix[start - 1]) / (prefix.length - start);
        }
        if (dp[start][K] != -1) return dp[start][K];

        double greatestAverage = 0;
        for (int i = start; i < prefix.length; i++) {
            greatestAverage = Math.max(greatestAverage, dfs(prefix, i + 1, K - 1, dp) + (prefix[i] - prefix[start - 1]) / (i - start + 1));
        }
        dp[start][K] = greatestAverage;
        return greatestAverage;
    }

}
