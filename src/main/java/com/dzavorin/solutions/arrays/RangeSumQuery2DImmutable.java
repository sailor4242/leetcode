package com.dzavorin.solutions.arrays;

public class RangeSumQuery2DImmutable {

    int[][] arr;

    public RangeSumQuery2DImmutable(int[][] mat) {

        if (mat.length == 0 || mat[0].length == 0) return;

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
        this.arr = arr;
    }

    public int sumRegion(int r1, int c1, int r2, int c2) {
        if (arr == null) return 0;

        r2 = Math.min(r2, arr.length - 1);
        c2 = Math.min(c2, arr[0].length - 1);
        r1 -= 1;
        c1 -= 1;
        int p = r1 < 0 || c1 < 0 ? 0 : arr[r1][c1];
        int t = r1 < 0 ? 0 : arr[r1][c2];
        int l = c1 < 0 ? 0 : arr[r2][c1];

        return arr[r2][c2] - t - l + p;
    }
}
