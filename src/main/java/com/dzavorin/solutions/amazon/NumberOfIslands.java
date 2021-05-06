package com.dzavorin.solutions.amazon;

import java.util.LinkedList;

public class NumberOfIslands {

    int[] directions = new int[]{0,1,0,-1,0};

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '0' || visited[i][j]) {
                    continue;
                }
                count++;
                LinkedList<int[]> list = new LinkedList<>();
                list.add(new int[]{i, j});
                visited[i][j] = true;
                while (!list.isEmpty()) {
                    int[] cur = list.removeFirst();
                    for (int d = 0; d < 4; d++) {
                        int di = cur[0] + directions[d];
                        int dj = cur[1] + directions[d + 1];
                        if (di >= 0 && di < n && dj >= 0 && dj < m && grid[di][dj] == '1' && !visited[di][dj]) {
                            list.add(new int[]{di, dj});
                            visited[di][dj] = true;
                        }
                    }
                }
            }
        }

        return count;
    }

}
