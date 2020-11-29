package com.dzavorin.solutions;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {

        Set<Character>[][] squares = new Set[3][3];
        Set<Character>[] columns = new Set[9];
        Set<Character>[] rows = new Set[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            columns[i] = new HashSet<>();
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j] = new HashSet<>();
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char ch = board[i][j];
                if (ch == '.') continue;
                if (!rows[i].add(ch)) return false;
                if (!columns[j].add(ch)) return false;
                if (!squares[i / 3][j / 3].add(ch)) return false;
            }
        }

        return true;
    }

}
