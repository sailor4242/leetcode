package com.dzavorin.solutions.sorting;

import java.util.Arrays;

public class SortMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {

        for (int i = 0; i < mat.length; i++) {
            sort(mat, i, 0);
        }

        for (int j = 0; j < mat[0].length; j++) {
            sort(mat, 0, j);
        }

        return mat;
    }

    void sort(int[][] mat, int lo, int hi) {

        int[] arr = new int[Math.min(mat.length - lo, mat[0].length - hi)];
        int i = lo;
        int j = hi;
        int k = 0;

        while (i < mat.length && j < mat[0].length) {
            arr[k++] = mat[i++][j++];
        }

        Arrays.sort(arr);

        i = lo;
        j = hi;
        k = 0;

        while (i < mat.length && j < mat[0].length) {
            mat[i++][j++] = arr[k++];
        }
    }

}
