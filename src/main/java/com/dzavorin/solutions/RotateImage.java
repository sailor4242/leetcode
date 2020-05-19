package com.dzavorin.solutions;

import java.util.Arrays;
import java.util.List;

public class RotateImage {

    public void rotate(int[][] m) {

        for (int i = 0; i < m.length/2; i ++) {
            int n = m[0].length - i;
            for (int j = i; j < m[0].length - i; j++) {

                int tmp = m[i][j];

                m[i][j] = m[n - 1][j];
                m[n - 1][j] = m[n - 1][n - 1];
                m[n - 1][n - 1] = m[i][n - 1];
                m[i][n - 1] = tmp;

            }
        }
    }
}
