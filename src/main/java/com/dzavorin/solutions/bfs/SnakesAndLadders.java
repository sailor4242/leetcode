package com.dzavorin.solutions.bfs;

import java.util.Arrays;
import java.util.LinkedList;

public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[] {1, 0});

        int min = Integer.MAX_VALUE;
        int n = board.length;
        int maxN = n * n;
        boolean[] visited = new boolean[maxN + 1];
        visited[1] = true;
        while (!list.isEmpty()) {
            int[] cur = list.removeFirst();
            if (cur[0] == maxN) {
                return cur[1];
            }

            for (int i = 1; i <= 6; i++) {
                if (cur[0] + i > maxN) break;

                int[] coords = getCoordsByNum(n, cur[0] + i);
                int nextNum = board[coords[0]][coords[1]];
                int next = nextNum != -1 ? nextNum : cur[0] + i;
                if (!visited[next]) {
                    visited[next] = true;
                    list.add(new int[] {next, cur[1] + 1});
                }
            }

        }

        return -1;
    }


    public static int[] getCoordsByNum(int n, int num) {
        int rem = (num - 1) % n;
        int row = n - 1 - (num - 1) / n;
        int col = row % 2 != n % 2 ? rem : n - 1 - rem;
        return new int[] {row, col};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getCoordsByNum(6, 7)));
    }
}
