package com.dzavorin.solutions.bfs;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {

        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Set<Character>[][] buckets = new Set[3][3];

        for (int i = 0; i < 9; i++) {
            rows.put(i, new HashSet<>());
            cols.put(i, new HashSet<>());
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buckets[i][j] = new HashSet<>();
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    rows.get(i).add(board[i][j]);
                    cols.get(j).add(board[i][j]);
                    buckets[i / 3][j / 3].add(board[i][j]);
                }
            }
        }

        PriorityQueue<Pair<int[], Integer>> pq =
                new PriorityQueue<>(Comparator.comparing(a -> a.getValue()));

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    int score = - rows.get(i).size() - cols.get(j).size()
                            - buckets[i / 3][j / 3].size();

                    pq.add(new Pair<>(new int[]{i, j}, score));
                }
            }
        }

        LinkedList<int[]> list = new LinkedList<>();
        while (!pq.isEmpty()) {
            list.add(pq.poll().getKey());
        }

        Set<Character>[][] possibles = new Set[3][3];

        while (!list.isEmpty()) {
            int[] cur = list.poll();
            int i = cur[0];
            int j = cur[1];

            Set<Character> row = rows.get(i);
            Set<Character> col = cols.get(j);
            Set<Character> bucket = buckets[i / 3][j / 3];

            List<Character> possible = new LinkedList<>();
            for (char n = '1'; n <= '9'; n++) {
                if (!row.contains(n) && !col.contains(n) && !bucket.contains(n)) {
                    possible.add(n);
                }
            }

            if (possible.size() == 1) {
                char n = possible.get(0);
                board[i][j] = n; // possible dfs
                row.add(n);
                col.add(n);
                bucket.add(n);
            } else {
                list.add(cur);
            }

        }

    }

    public static void main(String[] args) {
        char[][] arr = new char[][]
                {
                        {'5','3','.','.','7','.','.','.','.'},
                        {'6','.','.','1','9','5','.','.','.'},
                        {'.','9','8','.','.','.','.','6','.'},
                        {'8','.','.','.','6','.','.','.','3'},
                        {'4','.','.','8','.','3','.','.','1'},
                        {'7','.','.','.','2','.','.','.','6'},
                        {'.','6','.','.','.','.','2','8','.'},
                        {'.','.','.','4','1','9','.','.','5'},
                        {'.','.','.','.','8','.','.','7','9'}
                };
        new SudokuSolver().solveSudoku(arr);
        System.out.println(Arrays.deepToString(arr));

        char[][] arr2 = new char[][]
                {
                        {'.','.','9','7','4','8','.','.','.'},
                        {'7','.','.','.','.','.','.','.','.'},
                        {'.','2','.','1','.','9','.','.','.'},
                        {'.','.','7','.','.','.','2','4','.'},
                        {'.','6','4','.','1','.','5','9','.'},
                        {'.','9','8','.','.','.','3','.','.'},
                        {'.','.','.','8','.','3','.','2','.'},
                        {'.','.','.','.','.','.','.','.','6'},
                        {'.','.','.','2','7','5','9','.','.'}}
                ;
        new SudokuSolver().solveSudoku(arr2);
        System.out.println(Arrays.deepToString(arr2));
    }

}
