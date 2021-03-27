package com.dzavorin.solutions.dynamic;

public class JumpGame5 {

    public int maxJumps(int[] arr, int d) {
        Integer[] memo = new Integer[arr.length];
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res = Math.max(res, 1 + dfs(arr, d, i, memo));
        }
        return res;
    }

    private int dfs(int[] arr, int d, int index, Integer[] memo) {
        if (memo[index] != null) return memo[index];

        int res = 0;

        for (int i = 1; i <= d; i++) {
            if (index - i >= 0 && arr[index] > arr[index - i]) {
                res = Math.max(res, 1 + dfs(arr, d, index - i, memo));
            } else {
                break;
            }
        }

        for (int i = 1; i <= d; i++) {
            if (index + i < arr.length && arr[index] > arr[index + i]) {
                res = Math.max(res, 1 + dfs(arr, d, index + i, memo));
            } else {
                break;
            }
        }

        return memo[index] = res;
    }

}
