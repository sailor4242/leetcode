package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LargestPlusSign {

    // dp
    // https://www.youtube.com/watch?v=KAKLDuntvrE

    public int orderOfLargestPlusSign(int N, int[][] mines) {

        boolean[][] zeros = new boolean[N][N];
        int[][] dpR = new int[N][N];
        int[][] dpL = new int[N][N];
        int[][] dpB = new int[N][N];
        int[][] dpU = new int[N][N];

        for (int[] mine : mines) {
            zeros[mine[0]][mine[1]] = true;
        }


        for (int i = 1; i < N; i++) {
            int count = 0;
            for (int j = 1; j < N; j++) {
                if (!zeros[i - 1][j]) {
                    dpU[i][j] = dpU[i - 1][j] + 1;
                }
                if (!zeros[i][j - 1]) {
                    dpR[i][j] = dpR[i][j - 1] + 1;
                }
            }
        }

        for (int i = N - 2; i >= 0; i--) {
            int count = 0;
            for (int j = N - 2; j >= 0; j--) {
                if (!zeros[i][j + 1]) {
                    dpL[i][j] = dpL[i][j + 1] + 1;

                }
                if (!zeros[i + 1][j]) {
                    dpB[i][j] = dpB[i + 1][j] + 1;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!zeros[i][j]) {
                    res = Math.max(res, 1 + Math.min(
                            Math.min(dpR[i][j], dpL[i][j]),
                            Math.min(dpB[i][j], dpU[i][j])
                    ));
                }
            }
        }

        return res;
    }

    //// brute force BFS - TLE

    int[] plusDir = new int[]{0, -1, 0, 1, 0};

    public int orderOfLargestPlusSign2(int N, int[][] mines) {
        int res = 0;
        if (mines.length != N * N) {
            res = 1;
        } else {
            return 0;
        }

        boolean[][] zeros = new boolean[N][N];

        for (int[] mine : mines) {
            zeros[mine[0]][mine[1]] = true;
        }

        int maxOrder = N % 2 == 0 ? N / 2 : (N + 1) / 2;
        if (getPlusOrder(new int[]{N / 2, N / 2}, zeros) == maxOrder) {
            return maxOrder;
        }

        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{N / 2, N / 2});

        boolean[][] visited = new boolean[N][N];

        while (!list.isEmpty()) {

            int[] coords = list.removeFirst();
            int i = coords[0];
            int j = coords[1];
            visited[i][j] = true;
            int order = getPlusOrder(coords, zeros);
            res = Math.max(res, order);

            for (int d = 0; d < plusDir.length - 1; d++) {
                int id = plusDir[d] + i;
                int jd = plusDir[d + 1] + j;
                if (id >= 0 && id < N && jd >= 0 && jd < N
                        && !visited[id][jd]) {
                    list.add(new int[]{id, jd});
                }
            }
        }

        return res;
    }

    private int getPlusOrder(int[] coords, boolean[][] zeros) {
        if (zeros[coords[0]][coords[1]]) return 0;

        int res = 1;

        int[][] nextCoords = new int[4][2];
        for (int d = 0; d < plusDir.length - 1; d++) {
            nextCoords[d] = new int[]{coords[0] + plusDir[d], coords[1] + plusDir[d + 1]};
        }

        while (checkCoords(nextCoords, zeros)) {
            res++;
            for (int d = 0; d < plusDir.length - 1; d++) {
                nextCoords[d][0] = nextCoords[d][0] + plusDir[d];
                nextCoords[d][1] = nextCoords[d][1] + plusDir[d + 1];
            }
        }

        return res;
    }

    private boolean checkCoords(int[][] nextCoords, boolean[][] zeros) {
        int len = zeros.length;
        for (int[] coords : nextCoords) {
            if (checkCoords(coords[0], coords[1], zeros)) {
                return false;
            }
        }

        return true;
    }

    private boolean checkCoords(int i, int j, boolean[][] zeros) {
        return i < 0
                || i >= zeros.length
                || j < 0
                || j >= zeros.length
                || zeros[i][j];
    }
}
