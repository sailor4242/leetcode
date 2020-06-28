package com.dzavorin.solutions.dynamic;

import java.util.HashMap;
import java.util.Map;

public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }


    Map<Integer, Integer> map = new HashMap<>();

    public int numTrees2(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count += numTrees2(i - 1) * numTrees2(n - i);
        }
        map.put(n, count);
        return map.get(n);
    }
}
