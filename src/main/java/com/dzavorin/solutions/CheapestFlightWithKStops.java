package com.dzavorin.solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightWithKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Arrays.sort(flights, Comparator.<int[], Integer>comparing(f -> f[0])
                .thenComparing(f -> f[1])
                .thenComparing(f -> f[2]));

        int sum = Integer.MAX_VALUE;

        for (int i = src; i < flights.length; i++) {
            if (flights[i][0] == src) {
                sum = Math.min(sum, dfs(flights, i, dst, k, flights[i][2]));
            }
        }
        return sum == Integer.MAX_VALUE ? -1 : sum;
    }

    public int dfs(int[][] flights, int p, int dst, int k, int sum) {

        if (flights[p][1] == dst && k >= 0) {
            return sum;
        } else if (k < 0) {
            return Integer.MAX_VALUE;
        }

        int res = Integer.MAX_VALUE;
        for (int i = flights[p][1]; i < flights.length; i++) {
            if (flights[i][0] == flights[p][1]) {
                res = Math.min(res, dfs(flights, i, dst, k - 1, sum + flights[i][2]));
            }
        }

        return res;
    }

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        if (flights.length == 0)
            return -1;
        int[][] dp = new int[n][k + 2];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE - 10000;
            }
        }

        dp[src][0] = 0;
        for (int j = 1; j <= k + 1; j++) {
            dp[src][j] = 0;
            for (int[] flight : flights) {
                int start = flight[0];
                int end = flight[1];
                int cost = flight[2];
                dp[end][j] = Math.min(dp[end][j], Math.min(dp[start][j - 1] + cost, dp[end][j - 1]));
            }
        }
        return dp[dst][k + 1] >= Integer.MAX_VALUE - 10000 ? -1 : dp[dst][k + 1];
    }

    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int k) {
        int[][] cost = new int[n][n];
        for (int[] f : flights) {
            cost[f[0]][f[1]] = f[2];
        }

        Queue<int[]> q = new PriorityQueue<>(Comparator.comparing(f -> f[0]));
        q.add(new int[]{0, src, k + 1});

        while (!q.isEmpty()) {
            int[] top = q.remove();
            int price = top[0];
            int i = top[1];
            int stops = top[2];

            if (i == dst) {
                return price;
            }

            if (stops > 0) {
                for (int j = 0; j < n; j++) {
                    if (cost[i][j] != 0)
                        q.add(new int[]{price + cost[i][j], j, stops - 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new CheapestFlightWithKStops().findCheapestPrice(
                3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1)
        );
    }

}
