package com.dzavorin.solutions.dynamic;

public class CountSortedVowelStrings {

    String[] arr = new String[]{"a", "e", "i", "o", "u"};

    public int countVowelStrings(int n) {
        return dfs(n, 0, new Integer[n + 1][5]);
    }

    private int dfs(int n, int j, Integer[][] memo) {
        if (n == 0) {
            return 1;
        }
        if (memo[n][j] != null) return memo[n][j];

        int res = 0;
        for (int i = j; i < 5; i++) {
            res += dfs(n - 1, i, memo);
        }

        return memo[n][j] = res;
    }
    
}
