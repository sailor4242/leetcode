package com.dzavorin.solutions;

import java.util.LinkedList;

public class MaxAreaOfIsland {

    int[] dirs = new int []{0,1,0,-1,0};

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int area = 0;
                    LinkedList<int[]> list = new LinkedList<>();
                    list.add(new int[]{i, j});
                    area++;
                    grid[i][j] = -1;
                    while (!list.isEmpty()) {
                        int[] cur = list.removeLast();
                        for (int d = 0; d < 4; d++) {
                            int di = cur[0] + dirs[d];
                            int dj = cur[1] + dirs[d + 1];

                            if (di >= 0 && dj >= 0
                                    && di < grid.length && dj < grid[0].length
                                    && grid[di][dj] == 1) {
                                area++;
                                grid[di][dj] = -1;
                                list.add(new int[]{di, dj});
                            }
                        }
                    }
                    max = Math.max(max, area);
                }
            }
        }

        return max;
    }


    public static void main(String[] args) {
        System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(new int[][]{
                        {1,1,0,1,1},
                        {1,0,0,0,0},
                        {0,0,0,0,1},
                        {1,1,0,1,1}
        }));

        System.out.println(new MaxAreaOfIsland().maxAreaOfIsland(new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        }));
    }
}
