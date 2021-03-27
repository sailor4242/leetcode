package com.dzavorin.solutions.graph.djikstra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {

    private int[] dir = {0, 1, 0, -1, 0};

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(arr -> arr[2]));
        pq.add(new int[]{0, 0, 0});
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[0];
            int j = cur[1];
            if (i == n - 1 && j == m - 1) return dist[n - 1][m - 1];
            for (int d = 0; d < dir.length - 1; d++) {
                int di = i + dir[d];
                int dj = j + dir[d + 1];

                if (di >= 0 && dj >= 0 && di < n && dj < m) {
                    int diff = Math.max(cur[2], Math.abs(heights[i][j] - heights[di][dj]));
                    if (diff < dist[di][dj]) {
                        dist[di][dj] = diff;
                        pq.add(new int[]{di, dj, diff});
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new PathWithMinimumEffort().minimumEffortPath(new int[][]{{1, 10, 6, 7, 9, 10, 4, 9}}));
    }

}
