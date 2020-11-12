package com.dzavorin.solutions.bits;

import java.util.Arrays;

public class XORQueriesOfASubarray {

    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] prefix = new int[arr.length + 1];
        //xor with 0 does not change value
        prefix[0] = 0;
        for (int i = 1; i < prefix.length; i++) {
            System.out.println("-" + Integer.toBinaryString(prefix[i - 1]));
            System.out.println("--" + Integer.toBinaryString(arr[i - 1]));
            prefix[i] = prefix[i - 1] ^ arr[i - 1];
            System.out.println("---" + Integer.toBinaryString(prefix[i]));
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            result[i] = prefix[right + 1] ^ prefix[left];
        }
        return result;
    }

    /// MLE memo way

    public int[] xorQueries2(int[] arr, int[][] queries) {
        int[][] memo = new int[arr.length][arr.length];

        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }

        int[] res = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            res[i++] = getXorOf(arr, query[0], query[1], memo);
        }

        return res;
    }

    public int getXorOf(int[] arr, int begin, int end, int[][] memo) {

        if (begin == end) {
            return arr[begin];
        }
        if (memo[begin][end] != -1) {
            return memo[begin][end];
        }

        int res = 0;

        for (int i = begin; i <= end; i++) {

            res ^= arr[i];
            memo[begin][i] = res;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new XORQueriesOfASubarray().xorQueries(new int[]{1, 3, 4, 8},
                new int[][]{{0, 1}, {1, 2}, {0, 3}})));
    }
}
