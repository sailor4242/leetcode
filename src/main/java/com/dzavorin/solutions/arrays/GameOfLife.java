package com.dzavorin.solutions.arrays;

public class GameOfLife {

    int[][] directions = new int[][]{{1,1},{1,-1},{-1,1},{0,1},{1,0},{-1,0},{0,-1},{-1,-1}};

    public void gameOfLife(int[][] board) {
        int n = board.length;
        int m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int liveCount = 0;

                for (int d = 0; d < directions.length; d++) {
                    int[] direction = directions[d];
                    int di = i + direction[0];
                    int dj = j + direction[1];
                    if (di >= 0 && dj >= 0 && di < n && dj < m) {
                        if (board[di][dj] == 1 || board[di][dj] == -1) {
                            liveCount++;
                        }
                    }
                }

                if (board[i][j] == 0 && liveCount == 3) {
                    board[i][j] = 2;
                } else if (board[i][j] == 1 && (liveCount < 2 || liveCount > 3)) {
                    board[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                } else if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }

    }

}
