package com.dzavorin.solutions.dynamic;

import java.util.*;

public class MinimumNumberOfRefuelingStops {

    public int minRefuelStopsDP(int target, int startFuel, int[][] stations) {
        int len = stations.length;
        long[] dp = new long[len + 1];
        dp[0] = startFuel;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j > 0 && dp[j - 1] >= stations[i][0]; j--) {
                dp[j] = Math.max(dp[j], dp[j - 1] + stations[i][1]);
            }
        }
        for (int i = 0; i <= len; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    public int minRefuelStopsGreedy(int target, int startFuel, int[][] stations) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        long dist = startFuel;
        int res = 0;
        int i = 0;
        while (true) {
            while (i < stations.length && stations[i][0] <= dist) {
                queue.offer(stations[i][1]);
                i++;
            }

            if (dist >= target) {
                return res;
            }
            if (queue.isEmpty()) {
                return -1;
            }
            dist += queue.poll();
            res++;
        }

    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfRefuelingStops().minRefuelStopsDP(100, 1, new int[][]{{10, 100}}));
        System.out.println(new MinimumNumberOfRefuelingStops().minRefuelStopsDP(1, 1, new int[0][0]));
        System.out.println(new MinimumNumberOfRefuelingStops().minRefuelStopsDP(100, 10,
                new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}));
    }
}
