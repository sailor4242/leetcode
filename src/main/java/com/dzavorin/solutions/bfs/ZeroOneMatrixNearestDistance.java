package com.dzavorin.solutions.bfs;

import java.util.LinkedList;

// 542 - 01Matrix
public class ZeroOneMatrixNearestDistance {

    public int[][] updateMatrix(int[][] matrix) {

        LinkedList<int[]> list = new LinkedList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = Integer.MAX_VALUE;
                } else {
                    list.add(new int[]{i, j});
                }
            }
        }

        int[] directions = new int[] {0, 1, 0, -1, 0};

        while (!list.isEmpty()) {
            int[] cur = list.poll();
            int i = cur[0];
            int j = cur[1];
            for (int d = 0; d < directions.length - 1; d++) {
                int di = i + directions[d];
                int dj = j + directions[d + 1];
                if (di < matrix.length && di >= 0
                        && dj < matrix[0].length && dj >= 0
                        && matrix[di][dj] != 0
                        && matrix[i][j] + 1 < matrix[di][dj]) {
                    matrix[di][dj] = matrix[i][j] + 1;
                    list.add(new int[]{di, dj});
                }
            }
        }

        return matrix;
    }

}
