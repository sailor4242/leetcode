package com.dzavorin.solutions.dfs;

public class UniquePaths3 {

    int[] directions = new int[]{0, 1, 0, -1, 0};

    public int uniquePathsIII(int[][] grid) {
        int zeros = 0;
        int si = 0;
        int sj = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    zeros++;
                } else if (grid[i][j] == 1) {
                    si = i;
                    sj = j;
                }
            }
        }

        return dfs(grid, zeros, si, sj);
    }

    int dfs(int[][] grid, int zeros, int i, int j) {

        if (zeros < 0) {
            return 0;
        }

        grid[i][j] = -1;

        int res = 0;
        for (int d = 0; d < directions.length - 1; d++) {
            int di = i + directions[d];
            int dj = j + directions[d + 1];
            if (di >= 0 && di < grid.length && dj >= 0 && dj < grid[0].length) {
                if (grid[di][dj] == 0) {
                    res += dfs(grid, zeros - 1, di, dj);
                } else if (grid[di][dj] == 2 && zeros == 0) {
                    res += 1;
                }
            }
        }
        grid[i][j] = 0;

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths3()
                .uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
    }
}
