package com.dzavorin.solutions.dynamic;

public class MaximalSquare {

    public int maximalSquare(char[][] m) {

        int res = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] == '1') {
                    int k = 1;
                    int s = 1;
                    while (i + k < m.length && j + k < m[0].length) {
                        if (tt(i + k, j + k, i, j, m)) {

                            k++;
                            s = (k) * (k);

                        } else {
                            break;
                        }

                    }

                    res = Math.max(s, res);

                }
            }
        }
        return res;

    }

    public boolean tt(int l, int ll, int i, int j, char[][] m) {
        for (int e = i; e <= l; e++) {
            for (int ee = j; ee <= ll; ee++) {
                if (m[e][ee] == '0') {
                    return false;
                }
            }
        }
        return true;
    }

    public int maximalSquare2(char[][] matrix) {
        int R = matrix.length;
        int C = R > 0 ? matrix[0].length : 0;

        int[][] dp = new int[R + 1][C + 1];
        int max = 0;

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (matrix[i - 1][j - 1] == '1'){
                    dp[i][j] = 1 + Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                    max = Math.max(max, dp[i][j]);
                }
            }
        }

        return max * max;
    }

    public static void main(String[] args) {
//        System.out.println(new MaximalSquare().maximalSquare(new char[][]{{'1'}}));
        System.out.println(new MaximalSquare().maximalSquare(new char[][]{{'1', '0'}, {'1', '0'}}));
    }
}
