package com.dzavorin.solutions.bfs;

import java.util.*;

public class ShortestPathInBinaryMatrix {

    int[][] directions = new int[][]{{0, 1}, {1, 0} ,{-1, -1}, {-1, 0}, {0, -1},
            {1, 1}, {-1, 1}, {1, -1}};


    //bfs

    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return -1;
        if (m == 1 && n == 1) return 1;

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        grid[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();
            for (int[] d : directions) {
                int di = cur[0] + d[0];
                int dj = cur[1] + d[1];
                if (di >= 0 && di < m && dj >= 0 && dj < n && grid[di][dj] == 0) {
                    if (di == m - 1 && dj == n - 1) return grid[cur[0]][cur[1]] + 1;

                    queue.add(new int[]{di, dj});
                    grid[di][dj] = 1 + grid[cur[0]][cur[1]];

                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new ShortestPathInBinaryMatrix().shortestPathBinaryMatrix(
                new int[][]{{0,0,0,0},
                            {0,0,0,0},
                            {0,0,0,0},
                            {0,0,0,0}}));
    }

    ///// djikstra

    public int shortestPathBinaryMatrix2(int[][] grid) {
        if (grid.length == 0
                || grid[0].length == 0
                || grid[0][0] == 1
                || grid[grid.length - 1][grid[0].length - 1] == 1) {
            return -1;
        }

        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[2]));
        pq.add(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (cur[0] == grid.length - 1 && cur[1] == grid[0].length - 1) {
                return cur[2];
            }

            for (int[] d : directions) {
                int di = d[0] + cur[0];
                int dj = d[1] + cur[1];
                if (di >= 0 && dj >= 0
                        && di < grid.length
                        && dj < grid[0].length
                        && grid[di][dj] == 0) {

                    if (cur[2] + 1 < visited[di][dj]) {
                        pq.add(new int[]{di, dj, cur[2] + 1});
                        visited[di][dj] = cur[2] + 1;
                    }
                }

            }
        }

        return -1;
    }

}
