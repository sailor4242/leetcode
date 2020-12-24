package com.dzavorin.solutions.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int n = matrix.length;
        int m = matrix[0].length;

        boolean[][] visited = new boolean[n][m];

        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{0, 0});

        DirectionIt it = new DirectionIt();
        Direction prev = it.next();

        while (!list.isEmpty()) {
            int[] cur = list.removeFirst();
            int i = cur[0];
            int j = cur[1];

            res.add(matrix[i][j]);
            visited[i][j] = true;

            int xi = prev.x + i;
            int yj = prev.y + j;

            if (xi < n && yj < m && xi >= 0 && yj >=0 && !visited[xi][yj]) {
                list.add(new int[]{xi, yj});
            } else if (j + 1 < m && !visited[i][j + 1]) {
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

    static class DirectionIt {
        int c = 0;

        DirectionIt() {}

        Direction next() {
            return Direction.values()[c++ % 4];
        }
    }

}
