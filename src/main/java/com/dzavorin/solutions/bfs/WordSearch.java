package com.dzavorin.solutions.bfs;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class WordSearch {

    // bfs
    public boolean exist(char[][] board, String word) {

        if (word.isEmpty() || board.length == 0) {
            return false;
        }

        int[] directions = new int[]{0, 1, 0, -1, 0};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if (board[i][j] == word.charAt(0)) {

                    if (word.length() == 1) {
                        return true;
                    }

                    LinkedList<Pair<int[], Set<Pair<Integer, Integer>>>> list = new LinkedList<>();
                    Set<Pair<Integer, Integer>> visited = new HashSet<>();

                    visited.add(new Pair(i, j));
                    list.add(new Pair(new int[]{i, j, 1}, visited));


                    while (!list.isEmpty()) {
                        Pair<int[], Set<Pair<Integer, Integer>>> pair = list.removeLast();
                        int[] cur = pair.getKey();

                        for (int d = 0; d < directions.length - 1; d++) {

                            int di = cur[0] + directions[d];
                            int dj = cur[1] + directions[d + 1];

                            if (di >= 0 && di < board.length && dj >= 0 && dj < board[0].length
                                    && !pair.getValue().contains(new Pair(di, dj))
                                    && board[di][dj] == word.charAt(cur[2])) {

                                if (cur[2] == word.length() - 1) {
                                    return true;
                                }

                                Set<Pair<Integer, Integer>> nextVisited =
                                        new HashSet<>(pair.getValue());

                                nextVisited.add(new Pair(di, dj));

                                list.add(new Pair(new int[]{di, dj, cur[2] + 1}, nextVisited));

                            }
                        }
                    }
                }
            }
        }

        return false;

    }

    // dfs
    public boolean exist2(char[][] board, String word) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == word.charAt(0) && isFound(board, i, j, word, 0))
                    return true;

        return false;
    }

    private boolean isFound(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) return true;
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return false;
        if (word.charAt(index) != board[i][j]) return false;
        char temp = board[i][j];
        board[i][j] = '*';
        if (isFound(board, i + 1, j, word, index + 1) ||
                isFound(board, i - 1, j, word, index + 1) ||
                isFound(board, i, j + 1, word, index + 1) ||
                isFound(board, i, j - 1, word, index + 1))
            return true;
        board[i][j] = temp;
        return false;
    }

}
