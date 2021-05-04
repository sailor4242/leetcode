package com.dzavorin.solutions.binarysearch;

public class Search2DMatrix {

    public boolean searchMatrix(int[][] a, int target) {

        int n = a.length;
        int m = a[0].length;

        int i = 0;
        int j = m - 1;

        while (i < n && j >= 0) {

            if (a[i][j] == target) {
                return true;
            } else if (target > a[i][j]) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }
}

