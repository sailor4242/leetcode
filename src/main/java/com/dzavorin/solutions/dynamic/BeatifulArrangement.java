package com.dzavorin.solutions.dynamic;

public class BeatifulArrangement {

    public int countArrangement(int n) {
        return dp(n, 1, new boolean[n + 1]);
    }

    private int dp(int n, int i, boolean[] visited) {
        if (i == n + 1) {
            return 1;
        }

        int res = 0;
        for (int j = 1; j <= n; j++) {
            if (!visited[j] && (j % i == 0 || i % j == 0)) {
                visited[j] = true;
                res += dp(n, i + 1, visited);
                visited[j] = false;
            }
        }

        return res;
    }
}
