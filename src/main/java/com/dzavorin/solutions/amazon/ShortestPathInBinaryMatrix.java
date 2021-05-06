package com.dzavorin.solutions.amazon;

import java.util.LinkedList;

public class ShortestPathInBinaryMatrix {

    int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};

    public int shortestPathBinaryMatrix(int[][] grid) {

        int n = grid.length;
        if (n == 0) return -1;

        int m = grid[0].length;
        if (m == 0 || grid[0][0] == 1) return -1;

        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{0,0,1});

        while (!list.isEmpty()) {
            int[] cur = list.removeFirst();

            if (cur[0] == n - 1 && cur[1] == m - 1) {
                return cur[2];
            }

            for (int[] dir : dirs) {
                int i = cur[0] + dir[0];
                int j = cur[1] + dir[1];

                if (i >= 0 && i < n && j >= 0 && j < m && grid[i][j] == 0) {
                    grid[i][j] = 1;
                    list.add(new int[]{i,j,cur[2] + 1});
                }
            }
        }

        return -1;
    }

}
