package com.dzavorin.solutions.dfs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestIncreasingPathInMatrix {

    int[] directions = new int[]{0, 1, 0, -1, 0};

    public int longestIncreasingPath(int[][] matrix) {

        if (matrix.length < 1) return 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0]));

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                pq.add(new int[]{matrix[i][j], i, j});
            }
        }

        int res = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];

        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            res = Math.max(res, dfs(matrix, arr[1], arr[2], memo));
        }

        return res;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] memo) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        int max = 0;
        for (int d = 0; d < directions.length - 1; d++) {
            int di = i + directions[d];
            int dj = j + directions[d + 1];

            if (di < matrix.length && di >= 0
                    && dj < matrix[0].length && dj >= 0
                    && matrix[di][dj] > matrix[i][j]) {

                max = Math.max(max, dfs(matrix, di, dj, memo));
            }

        }

        memo[i][j] = 1 + max;

        return memo[i][j];
    }
}
