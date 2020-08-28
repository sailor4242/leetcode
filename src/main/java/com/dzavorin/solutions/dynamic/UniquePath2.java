package com.dzavorin.solutions.dynamic;

import java.util.HashMap;
import java.util.Map;

public class UniquePath2 {

    public int uniquePathsWithObstacles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (grid[i - 1][j - 1] == 1) {
                    dp[i][j] = 0;
                } else if (i == 1 && j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        return dp[n][m];
    }

    public int uniquePathsWithObstacles2(int[][] grid) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1) return 0;
        return helper(grid, 0, 0, new HashMap<>());
    }

    private int helper(int[][] grid, int i, int j, Map<String, Integer> memo) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1) {
            return 0;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return 1;
        }

        String key = i + "-" + j;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int val = helper(grid, i + 1, j, memo) + helper(grid, i, j + 1, memo);
        memo.put(key, val);

        return memo.get(key);
    }

}
