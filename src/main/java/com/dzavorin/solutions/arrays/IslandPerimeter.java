package com.dzavorin.solutions.arrays;

public class IslandPerimeter {

    public int islandPerimeter(int[][] grid) {

        int[] directions = new int[]{0,1,0,-1,0};
        int res = 0;
        for (int i = 0; i < grid.length;i ++) {
            for (int j = 0; j < grid[0].length ;j ++) {
                if (grid[i][j] == 1) {

                    for (int d = 0; d < directions.length - 1; d++) {
                        int di = i + directions[d];
                        int dj = j + directions[d + 1];

                        if (!(di < grid.length && di >= 0 && dj < grid[0].length && dj >= 0)
                                || grid[di][dj] == 0) {
                            res++;
                        }

                    }

                }
            }
        }

        return res;

    }

}
