package com.dzavorin.solutions.dynamic;

import java.util.Arrays;

public class MinimumFaillingSum {

    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        int[] dp = new int[n];

        int prev, cur, next;

        for (int i = 0; i < n; i++) {
            prev = Integer.MAX_VALUE;
            cur = dp[0];
            for (int j = 0; j < n; j++) {
                next = j + 1 < n ? dp[j + 1] : Integer.MAX_VALUE;
                dp[j] = A[i][j] + Math.min(dp[j], Math.min(prev, next));
                prev = cur;
                cur = next;
            }
        }

        return Arrays.stream(dp).min().getAsInt();
    }

    public int minFallingPathSum2(int[][] A) {
        for (int i = 1; i < A.length; i++)
            for (int j = 0; j < A.length; j++)
                A[i][j] += Math.min(
                        A[i - 1][j],
                        Math.min(A[i - 1][Math.max(0, j - 1)],
                                A[i - 1][Math.min(A.length - 1, j + 1)]
                        )
                );


            return Arrays.stream(A[A.length - 1]).min().getAsInt();
    }

    public static void main(String[] args) {
        System.out.println(new MinimumFaillingSum().minFallingPathSum(new int[][]{{1,2,3}, {4,5,6},{7,8,9}}));
    }


}
