package com.dzavorin.solutions.dfs;

import java.util.Arrays;

public class RegionsCutBySlashes {

    public int regionsBySlashes(String[] grid) {

        int[][] arr = new int[grid.length * 3][grid[0].length() * 3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                fillSquare(arr, i, j, grid[i].charAt(j));
            }
        }

        for (int[] a: arr) {
            System.out.println(Arrays.toString(a));
        }
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    expand(arr, i, j);
                    count++;
                }
            }
        }


        return count;
    }

    private void expand(int[][] arr, int i, int j) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length) {
            return;
        }


        if (arr[i][j] == 0) {
            arr[i][j] = -1;
            expand(arr, i + 1, j);
            expand(arr, i - 1, j);
            expand(arr, i, j + 1);
            expand(arr, i, j - 1);
        }

    }

    private void fillSquare(int[][] grid, int i, int j, char ch) {
        if (ch == '\\') {
            grid[i * 3][j * 3] = 1;
            grid[i * 3 + 1][j * 3 + 1] = 1;
            grid[i * 3 + 2][j * 3 + 2] = 1;
        } else if (ch == '/') {
            grid[i * 3][j * 3 + 2] = 1;
            grid[i * 3 + 1][j * 3 + 1] = 1;
            grid[i * 3 + 2][j * 3] = 1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new RegionsCutBySlashes().regionsBySlashes(new String[]{"//","/ "}));
    }
}
