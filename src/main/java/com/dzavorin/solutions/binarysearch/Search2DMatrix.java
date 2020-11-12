package com.dzavorin.solutions.binarysearch;

public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int clo = 0;
        int chi = matrix.length - 1;

        while (clo <= chi) {
            int cmid = clo + (chi - clo / 2);
            int m = matrix[cmid][matrix[0].length - 1];
            if (m == target) {
                return true;
            } else if (m < target) {
                clo = cmid + 1;
            } else {
                chi = cmid - 1;
            }
        }

        if (clo > matrix.length - 1) return false;

        int rlo = 0;
        int rhi = matrix[0].length - 1;

        while (rlo <= rhi) {
            int rmid = rlo + (rhi - rlo / 2);
            int m = matrix[clo][rmid];
            if (m == target) {
                return true;
            } else if (m < target) {
                rlo = rmid + 1;
            } else {
                rhi = rmid - 1;
            }
        }

        return false;
    }

}
