package com.dzavorin.solutions.arrays;

public class MaxIncreaseToKeepCitySkyline {

    public int maxIncreaseKeepingSkyline(int[][] grid) {

        int total = 0;

        int[] vs = new int[grid[0].length];
        int[] hs = new int[grid.length];

        for (int i = 0; i < grid.length; i++) {
            int hMax = 0;
            for (int j = 0; j < grid[0].length; j++) {

                int cur = grid[i][j];
                hMax = Math.max(hMax, cur);
                vs[j] = Math.max(vs[j], cur);
            }
            hs[i] = hMax;
        }

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                total += Math.min(vs[j], hs[i]) - grid[i][j];

            }

        }


        return total;
    }

    public static void main(String[] args) {
        System.out.println(new MaxIncreaseToKeepCitySkyline().maxIncreaseKeepingSkyline(new int[][]{{
            3,0,8,4},{2,4,5,7},{9,2,6,3},{0,3,1,0
        }}));
    }
}
