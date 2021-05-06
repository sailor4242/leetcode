package com.dzavorin.solutions.dynamic;

import java.util.ArrayList;
import java.util.List;

public class OnesAndZeros {

    public int findMaxForm(String[] strs, int m, int n) {
        List<Count> counts = new ArrayList<>();
        for (String str : strs) {
            int ones = 0;
            int zeros = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    zeros++;
                } else {
                    ones++;
                }
            }
            counts.add(new Count(ones, zeros));
        }

        return dfs(counts, 0, m, n, new Integer[strs.length][m + 1][n + 1]);
    }

    private int dfs(List<Count> counts, int i, int m, int n, Integer[][][] memo) {
        if (i == counts.size()) {
            return 0;
        }

        if (memo[i][m][n] != null) return memo[i][m][n];

        int res = Integer.MIN_VALUE;

        Count c = counts.get(i);
        if (c.zeros <= m && c.ones <= n) {
            res = 1 + Math.max(res, dfs(counts, i + 1, m - c.zeros, n - c.ones, memo));
        }
        res = Math.max(res, dfs(counts, i + 1, m, n, memo));


        return memo[i][m][n] = res;
    }

    static class Count {
        int ones;
        int zeros;

        public Count(int ones, int zeros) {
            this.ones = ones;
            this.zeros = zeros;
        }

    }

}
