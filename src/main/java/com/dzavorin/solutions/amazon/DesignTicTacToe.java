package com.dzavorin.solutions.amazon;

import java.util.HashMap;
import java.util.Map;

public class DesignTicTacToe {

    int[][] board;
    int n;
    Map<Integer, int[][]> playerToCounts;

    public DesignTicTacToe(int n) {
        this.n = n;
        this.board = new int[n][n];
        this.playerToCounts = new HashMap<>();
    }

    public int move(int row, int col, int player) {
        if (board[row][col] != 0) return -1;
        board[row][col] = player;

        if (!playerToCounts.containsKey(player)) {
            playerToCounts.put(player, new int[3][n]);
        }

        int[][] counts = playerToCounts.get(player);

        counts[0][row]++;
        counts[1][col]++;

        if (row == col) {
            counts[2][0]++;
        }

        if (row + col + 1 == n) {
            counts[2][1]++;
        }

        if (counts[0][row] == n || counts[1][col] == n || counts[2][0] == n || counts[2][1] == n) {
            return player;
        }

        return 0;
    }

}
