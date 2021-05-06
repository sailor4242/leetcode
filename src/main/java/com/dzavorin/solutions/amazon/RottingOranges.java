package com.dzavorin.solutions.amazon;

import java.util.LinkedList;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int countFresh = 0;
        LinkedList<int[]> rotten = new LinkedList<>();
        int time = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    countFresh++;
                } else if (grid[i][j] == 2) {
                    rotten.add(new int[]{i,j});
                }
            }
        }

        int[] dirs = new int[]{0,1,0,-1,0};

        while (!rotten.isEmpty()) {
            LinkedList<int[]> nextRotten = new LinkedList<>();
            while (!rotten.isEmpty()) {
                int[] cur = rotten.removeFirst();
                for (int d = 0; d < 4; d++) {
                    int i = cur[0] + dirs[d];
                    int j = cur[1] + dirs[d + 1];
                    if (i >= 0 && i < n && j >=0 && j < m && grid[i][j] == 1) {
                        countFresh--;
                        grid[i][j] = 2;
                        nextRotten.add(new int[]{i, j});
                    }
                }
            }
            if (nextRotten.size() != 0) {
                time++;
            }
            rotten = nextRotten;
        }

        return countFresh == 0 ? time : -1;
    }

}
