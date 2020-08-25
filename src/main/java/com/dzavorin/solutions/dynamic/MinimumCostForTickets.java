package com.dzavorin.solutions.dynamic;

public class MinimumCostForTickets {

    int[] durations = new int[] {1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {

        int[] memo = new int[days.length];

        return dp(0, days, costs, memo);
    }

    public int dp(int i, int[] days, int[] costs, int[] memo) {
        if (i >= days.length) return 0;
        if (memo[i] != 0) return memo[i];

        int min = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < costs.length; k++) {
            while (j < days.length && days[j] < days[i] + durations[k]) {
                j++;
            }
            min = Math.min(min, costs[k] + dp(j, days, costs, memo));
        }

        memo[i] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumCostForTickets().mincostTickets(
                new int[]{1, 4, 6, 7, 8, 20},
                new int[]{2, 7, 15})
        );
    }
}
