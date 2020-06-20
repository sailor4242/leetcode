package com.dzavorin.solutions.bfs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        if (n == 0) {
            return 0;
        }
        int t = grid[0][0];

        boolean[][] visited = new boolean[n][n];
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(node -> node.val));
        queue.add(new Node(0,0, t));
        visited[0][0] = true;
        int[] directions = new int[]{0, 1, 0, -1, 0};
        while (!queue.isEmpty()) {

            Node cur = queue.poll();
            t = Math.max(t, grid[cur.i][cur.j]);
            if (cur.i == n - 1 && cur.j == n - 1) {
                return t;
            }

            for (int d = 0; d < directions.length - 1; d++) {

                int in = cur.i + directions[d];
                int jn = cur.j + directions[d + 1];

                if (in < n && in >= 0 && jn < n && jn >= 0 && !visited[in][jn]) {
                    queue.add(new Node(in, jn, grid[in][jn]));
                    visited[in][jn] = true;
                }
            }
        }
        return t;
    }

    static class Node {
        int i;
        int j;
        int val;

        Node(int i, int j, int val) {
            this.i=i;
            this.j=j;
            this.val=val;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SwimInRisingWater()
                .swimInWater(new int[][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}}));
        System.out.println(new SwimInRisingWater()
                .swimInWater(new int[][]{{10,12,4,6},{9,11,3,5},{1,7,13,8},{2,0,15,14}}));
    }
}
