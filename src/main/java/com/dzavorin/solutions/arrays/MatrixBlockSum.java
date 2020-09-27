package com.dzavorin.solutions.arrays;

import java.util.Arrays;

public class MatrixBlockSum {

    // 2-d prefix sum
    public int[][] matrixBlockSum(int[][] mat, int K) {

        int[][] arr = new int[mat.length][mat[0].length];

        arr[0][0] = mat[0][0];

        for (int i = 1; i < arr[0].length; i++ ) {
            arr[0][i] = arr[0][i - 1] + mat[0][i];
        }

        for (int i = 1; i < arr.length; i++ ) {
            arr[i][0] = arr[i - 1][0] + mat[i][0];
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[0].length; j++) {
                arr[i][j] = mat[i][j] + arr[i][j - 1] + arr[i - 1][j] - arr[i - 1][j - 1];
            }
        }

        int[][] res = new int[mat.length][mat[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                res[i][j] = rangeSum(arr,
                        i - K - 1,
                        j - K - 1,
                        Math.min(i + K, arr.length - 1),
                        Math.min(j + K, arr[0].length - 1)
                );
            }
        }

        return res;
    }

    private int rangeSum(int[][] arr, int r1, int c1, int r2, int c2) {
        int p = r1 < 0 || c1 < 0 ? 0 : arr[r1][c1];
        int t = r1 < 0 ? 0 : arr[r1][c2];
        int l = c1 < 0 ? 0 : arr[r2][c1];

        return arr[r2][c2] - t - l + p;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new MatrixBlockSum().matrixBlockSum(
                new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}}, 1
        )));
    }
}
