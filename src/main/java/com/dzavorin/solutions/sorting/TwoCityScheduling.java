package com.dzavorin.solutions.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class TwoCityScheduling {

    public int twoCitySchedCost(int[][] costs) {
        int[] i = new int[]{0};
        Optional<Integer> res = Arrays.stream(costs)
                .sorted(Comparator.comparing(c -> c[0] - c[1]))
                .map(arr -> {
                    if (i[0] < costs.length / 2) {
                        return arr[0];
                    } else {
                        return arr[1];
                    }
                })
                .reduce(Integer::sum);

        return res.orElse(0);
    }

    public int twoCitySchedCostDP(int[][] cs) {
        int n = cs.length >> 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + cs[i - 1][0];
            dp[0][i] = dp[0][i - 1] + cs[i - 1][1];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + cs[i + j - 1][0],
                        dp[i][j - 1] + cs[i + j - 1][1]);
            }
        }
        return dp[n][n];
    }

    public static void main(String[] args) {
        System.out.println(new TwoCityScheduling().twoCitySchedCost(new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}}));
    }

}
