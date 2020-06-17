package com.dzavorin.solutions.bfs;

import java.util.*;

public class SurroundedRegions {

    public void solve(char[][] board) {
        int n = board.length;

        if (n == 0) {
            return;
        }

        int m = board[0].length;


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'X' || board[i][j] == 'F') {
                    continue;
                }

                LinkedList<Pair> bfs = new LinkedList<>();
                Pair pair = new Pair(i, j);
                bfs.add(pair);

                Set<Pair> visited = new HashSet<>();
                visited.add(pair);

                int[] sides = new int[]{0, 1, 0, -1, 0};

                boolean isSafe = false;

                while (!bfs.isEmpty()) {
                    Pair cur = bfs.removeLast();
                    if (cur.i == 0 || cur.i == n - 1 || cur.j == 0 || cur.j == m - 1)  {
                        isSafe = true;
                    }
                    for (int k = 0; k < sides.length - 1; k++) {
                        int ik = cur.i + sides[k];
                        int jk = cur.j + sides[k + 1];
                        if (ik >= 0 && ik < n && jk >= 0 && jk < m && board[ik][jk] == 'O') {
                            Pair p = new Pair(ik, jk);
                            if (!visited.contains(p)) {
                                bfs.add(p);
                                visited.add(p);
                            }
                        }
                    }
                }
                if (isSafe) {
                    for (Pair p : visited) {
                        board[p.i][p.j] = 'F';
                    }
                } else {
                    for (Pair p : visited) {
                        board[p.i][p.j] = 'X';
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'F') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    static class Pair {
        Integer i;
        Integer j;

        Pair(Integer i, Integer j) {
            this.i = i;
            this.j = j;
        }

        public int hashCode() {
            return Objects.hashCode(i) ^ Objects.hashCode(j);
        }

        public boolean equals(Object o) {
            if (o == this) return true;
            if (o instanceof Pair) {
                Pair p = (Pair) o;
                return Objects.equals(this.i, p.i) && Objects.equals(this.j, p.j);
            }
            return false;
        }

    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        new SurroundedRegions().solve(arr);
        System.out.println(Arrays.deepToString(arr));
    }

}
