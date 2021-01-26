package com.dzavorin.solutions.arrays;

import java.util.LinkedList;

public class SpiralMatrix2 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int row = 0;
        int col = 0;
        while (cnt <= n * n) {
            result[row][col] = cnt++;
            int r = Math.floorMod(row + dir[d][0], n);
            int c = Math.floorMod(col + dir[d][1], n);

            // change direction if next cell is non zero
            if (result[r][c] != 0) d = (d + 1) % 4;

            row += dir[d][0];
            col += dir[d][1];
        }
        return result;
    }

    ////

    public int[][] generateMatrix2(int n) {
        int[][] res = new int[n][n];

        boolean[][] visited = new boolean[n][n];

        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{0, 0});

        int k = 1;
        Direction prev = Direction.RIGHT;

        while (!list.isEmpty()) {
            int[] cur = list.removeFirst();
            int i = cur[0];
            int j = cur[1];

            res[i][j] = k++;
            visited[i][j] = true;

            int xi = prev.x + i;
            int yj = prev.y + j;

            if (xi < n && yj < n && xi >= 0 && yj >=0 && !visited[xi][yj]) {
                list.add(new int[]{xi, yj});
            } else if (j + 1 < n && !visited[i][j + 1]) {
                list.add(new int[]{i, j + 1});
                prev = Direction.RIGHT;
            } else if (i + 1 < n && !visited[i + 1][j]) {
                list.add(new int[]{i + 1, j});
                prev = Direction.DOWN;
            } else if (j - 1 >= 0 && !visited[i][j - 1]) {
                list.add(new int[]{i, j - 1});
                prev = Direction.LEFT;
            } else if (i - 1 >= 0 && !visited[i - 1][j]) {
                list.add(new int[]{i - 1, j});
                prev = Direction.UP;
            }

        }

        return res;
    }

    enum Direction {
        RIGHT(0, 1),
        DOWN(-1, 0),
        LEFT(0, -1),
        UP(-1, 0);

        int x;
        int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
