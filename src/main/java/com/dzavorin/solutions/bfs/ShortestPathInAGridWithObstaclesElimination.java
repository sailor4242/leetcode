package com.dzavorin.solutions.bfs;

import java.util.*;

public class ShortestPathInAGridWithObstaclesElimination {

    public int shortestPath(int[][] grid, int k) {

        LinkedList<Tuple> list = new LinkedList<>();

        list.add(new Tuple(0,0,0, 0));

        int n = grid.length;
        int m = grid[0].length;

        boolean[][][] visited = new boolean[n][m][m * n];
        visited[0][0][0] = true;

        int[] dir = new int[]{0,1,0,-1,0};

        while (!list.isEmpty()) {

            Tuple tuple = list.pop();

            if (tuple.i == n - 1 && tuple.j == m - 1) {
                return tuple.s;
            }

            int obs = grid[tuple.i][tuple.j];

            for (int i = 0; i < dir.length - 1; i ++) {

                int ni = tuple.i + dir[i];
                int nj = tuple.j + dir[i + 1];
                int nk = tuple.k + obs;

                if (ni >= 0 && ni < n && nj >= 0 && nj < m && nk <= k && !visited[ni][nj][nk]) {
                    visited[ni][nj][nk] = true;
                    list.add(new Tuple(ni, nj, tuple.s + 1, nk));
                }
            }

        }

        return -1;
    }

    static class Tuple {
        int i;
        int j;
        int s;
        int k;

        public Tuple(int i, int j, int s, int k) {
            this.i = i;
            this.j = j;
            this.s = s;
            this.k = k;
        }
    }

    public static void main(String[] args) {
        System.out.println(new ShortestPathInAGridWithObstaclesElimination().shortestPath(
            new int[][]{{0,0,0},
                        {1,1,0},
                        {0,0,0},
                        {0,1,1},
                        {0,0,0}}, 1
        ));

        System.out.println(new ShortestPathInAGridWithObstaclesElimination().shortestPath(
            new int[][]{{0,1,0,0,0,1,0,0},
                        {0,1,0,1,0,1,0,1},
                        {0,0,0,1,0,0,1,0}}, 1
        ));

    }

}
