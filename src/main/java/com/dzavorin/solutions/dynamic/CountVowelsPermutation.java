package com.dzavorin.solutions.dynamic;

import java.util.Map;

public class CountVowelsPermutation {
    int mod = 1000000000 + 7;

    Map<Character, Integer> all = Map.of(
            'a', 1,
            'e', 2,
            'i', 3,
            'o', 4,
            'u', 5
    );

    Map<Character, char[]> map = Map.of(
            'a', new char[] {'e'},
            'e', new char[] {'a', 'i'},
            'i', new char[] {'a', 'e', 'o', 'u'},
            'o', new char[] {'i', 'u'},
            'u', new char[] {'a'}
    );

    public int countVowelPermutation(int n) {
        return dfs(n, new StringBuilder("d"), new Integer[n + 2][6]);
    }

    private int dfs(int n, StringBuilder sb, Integer[][] memo) {
        if (sb.length() == n + 1) {
            return 1;
        }
        char l =  sb.charAt(sb.length() - 1);
        int last = l == 'd' ? 0 : all.get(l);
        if (memo[sb.length()][last] != null) {
            return memo[sb.length()][last];
        }

        int res = 0;
        if (sb.length() == 1) {
            for (char ch : all.keySet()) {
                sb.append(ch);
                res = (res + dfs(n, sb, memo) % mod) % mod;
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            var allowed = map.get(sb.charAt(sb.length() - 1));
            for (char ch : allowed) {
                sb.append(ch);
                res = (res + dfs(n, sb, memo) % mod) % mod;
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return memo[sb.length()][last] = res;
    }

    ////

    Integer dp[][];
    int arr[][] = new int[][]{{1}, {0, 2}, {0, 1, 3, 4}, {2, 4}, {0}};

    int find(int size, int n, int curr) {
        if (size == n - 1)
            return 1;
        if (dp[size][curr] != null)
            return dp[size][curr];
        int count = 0;
        for (int d : arr[curr]) {
            dp[size + 1][d] = find(size + 1, n, d);
            count = (count + dp[size + 1][d]) % mod;
        }
        return dp[size][curr] = count;
    }

    public int countVowelPermutation2(int n) {
        dp = new Integer[n + 1][5];
        int count = 0;
        for (int i = 0; i < 5; i++)
            count = (count + find(0, n, i)) % mod;
        return count;
    }

}
