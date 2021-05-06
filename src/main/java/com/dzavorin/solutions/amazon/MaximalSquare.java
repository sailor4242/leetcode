package com.dzavorin.solutions.amazon;

public class MaximalSquare {

    //square - квадрат, не прямоугольник!
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n + 1][m + 1];

        int res = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                    res = Math.max(res, dp[i][j]);
                }
            }
        }

        return res * res;
    }
}
