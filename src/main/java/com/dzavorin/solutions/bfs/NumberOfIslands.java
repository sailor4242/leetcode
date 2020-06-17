package com.dzavorin.solutions.bfs;

public class NumberOfIslands {

    public int numIslands(char[][] grid) {

        int count = 0;
        int g = grid.length;
        if (g == 0) {
            return count;
        }
        int gg = grid[0].length;

        for (int i = 0; i < g; i++) {
            for (int j = 0; j < gg; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    spread(grid, i, j);
                }
            }

        }

        return count;
    }

    public void spread(char[][] grid, int i, int j) {
        int g = grid.length;
        int gg = grid[0].length;

        if (i < 0 || i >= g || j < 0 || j >= gg || grid[i][j] != '1') {
            return;
        }

        grid[i][j] = '2';

        spread(grid, i-1, j);
        spread(grid, i+1, j);
        spread(grid, i, j-1);
        spread(grid, i, j+1);

    }

    public static void main(String[] args) {
        System.out.println(new NumberOfIslands().numIslands(new char[][]{}));
        //[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
    }

}
