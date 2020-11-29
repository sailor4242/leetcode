package com.dzavorin.solutions.dynamic;

import java.util.HashMap;
import java.util.Map;

public class OutOfBoundaryPath {

    int[] directions = new int[]{0, 1, 0, -1, 0};
    int mod = 1000000007;

    public int findPaths(int m, int n, int N, int i, int j) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(m, n, N, i, j, memo);
    }

    private int dfs(int m, int n, int N, int i, int j, Map<String, Integer> memo) {

        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (N == 0) {
            return 0;
        }
        String key = i + "_" + j + "_" + N;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int res = 0;
        for (int d = 0; d < directions.length - 1; d++) {
            res = (res + dfs(m, n, N - 1, i + directions[d], j + directions[d + 1], memo)) % mod;
        }

        memo.put(key, res);
        return res;
    }

}
