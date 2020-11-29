package com.dzavorin.solutions;

public class JumpGame3 {

    public boolean canReach(int[] arr, int start) {
        return dfs(arr, start, new Boolean[arr.length]);
    }

    private boolean dfs(int[] arr, int i, Boolean[] memo) {
        if (i < 0 || i >= arr.length) return false;
        if (arr[i] == 0) return true;
        if (memo[i] != null) return memo[i];
        memo[i] = false;
        boolean res = dfs(arr, i + arr[i], memo) || dfs(arr, i - arr[i], memo);
        memo[i] = res;
        return res;
    }


    public boolean canReach2(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            if (arr[start] == 0) {
                return true;
            }
            arr[start] = -arr[start];
            return canReach2(arr, start + arr[start]) || canReach2(arr, start - arr[start]);
        }
        return false;
    }
}
