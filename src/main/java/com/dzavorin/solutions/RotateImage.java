package com.dzavorin.solutions;

public class RotateImage {

    public void rotate(int[][] m) {

        int len = m[0].length - 1;

        for (int i = 0; i < m.length/2 ; i++) {

            for (int j = i; j < len - i; j++) {

                int temp = m[i][j];
                m[i][j] = m[len - j][i];
                m[len - j][i] = m[len - i][len - j];
                m[len - i][len - j] = m[j][len - i];
                m[j][len - i] = temp;
            }
        }
    }
}
