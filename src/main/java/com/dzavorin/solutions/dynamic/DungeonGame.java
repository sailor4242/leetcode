package com.dzavorin.solutions.dynamic;

public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 1;

        int N = dungeon.length;
        int M = dungeon[0].length;
        int[][] dp = new int[N][M];
        dp[N - 1][M - 1] = Math.max(1 - dungeon[N - 1][M - 1], 1);

        for (int i = N - 1; i >= 0; --i) {
            for (int j = M - 1; j >= 0; --j) {
                if (i == N - 1 && j == M - 1) continue;
                int HP_D = i + 1 == N ? Integer.MAX_VALUE : dp[i + 1][j] - dungeon[i][j];
                int HP_R = j + 1 == M ? Integer.MAX_VALUE : dp[i][j + 1] - dungeon[i][j];
                int HP = Math.min(HP_D, HP_R);
                dp[i][j] = Math.max(HP, 1);
            }
        }

        return dp[0][0];
    }
}
