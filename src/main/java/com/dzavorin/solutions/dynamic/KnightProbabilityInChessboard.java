package com.dzavorin.solutions.dynamic;

public class KnightProbabilityInChessboard {

    int[][] dirs = new int[][]{{-2, 1}, {-1, 2}, {2, 1}, {1, 2}, {2, -1}, {1, -2}, {-2, -1}, {-1, -2}};

    public double knightProbability(int N, int K, int r, int c) {
        return dfs(new int[]{r, c}, new Double[N][N][K + 1], N, K);
    }

    private double dfs(int[] coord, Double[][][] memo, int N, int K) {
        if (K == 0) {
            return 1;
        }
        if (memo[coord[0]][coord[1]][K] != null) {
            return memo[coord[0]][coord[1]][K];
        }

        double res = 0;
        for (int[] dir : dirs) {
            int[] next = new int[]{coord[0] + dir[0], coord[1] + dir[1]};
            if (isOnTheBoard(next, N)) {
                res += dfs(next, memo, N, K - 1) / dirs.length;
            }
        }

        return memo[coord[0]][coord[1]][K] = res;
    }

    private boolean isOnTheBoard(int[] coords, int N) {
        return coords[0] < N && coords[0] >= 0 && coords[1] < N && coords[1] >= 0;
    }

    public static void main(String[] args) {
        System.out.println(new KnightProbabilityInChessboard().knightProbability(3, 2, 0 , 0));
    }
}
