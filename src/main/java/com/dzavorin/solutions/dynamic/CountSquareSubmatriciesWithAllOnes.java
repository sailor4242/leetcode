package com.dzavorin.solutions.dynamic;

public class CountSquareSubmatriciesWithAllOnes {

    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int[][] arr = new int[n + 1][matrix[0].length + 1];

        int count = 0;

        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j <= matrix[0].length; j ++) {

                if (matrix[i-1][j-1] == 1) {

                    int k = Math.min(Math.min(arr[i-1][j], arr[i][j-1]), arr[i-1][j-1]);
                    arr[i][j] = k + 1;
                    count += k + 1;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new CountSquareSubmatriciesWithAllOnes()
        .countSquares(new int[][]{{0,1,1,1}, {1,1,1,1}, {0,1,1,1}}));
    }
}
