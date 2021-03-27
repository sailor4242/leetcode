package com.dzavorin.solutions;

import java.util.Arrays;

public class ScoreAfterFlippingMatrix {

    public int matrixScore(int[][] A) {

        int countOnes = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i][0] == 0) {
                flipRow(A[i]);
            }
        }

        for (int j = 1; j < A[0].length; j++) {
            int count = 0;
            for (int i = 0; i < A.length; i++) {
                if (A[i][j] == 0) {
                    count++;
                }
            }

            if (count > A.length / 2) {
                flipCol(A, j);
            }
        }

        //System.out.println(Arrays.deepToString(A));

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 1) {
                    res += Math.pow(2, A[0].length - j - 1);
                }
            }
        }

        return res;
    }

    private void flipRow(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                arr[i] = 1;
            } else {
                arr[i] = 0;
            }
        }
    }

    private void flipCol(int[][] arr, int j) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][j] == 0) {
                arr[i][j] = 1;
            } else {
                arr[i][j] = 0;
            }
        }
    }

}
