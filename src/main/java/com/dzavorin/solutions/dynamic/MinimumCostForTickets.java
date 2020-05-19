package com.dzavorin.solutions.dynamic;

import java.util.Arrays;

public class MinimumCostForTickets {

    int[] days, costs;
    int[] memo;
    int[] durations = new int[]{1, 7, 30};

    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new int[days.length];

        return dp(0);
    }

    public int dp(int i) {
        if (i >= days.length)
            return 0;
        if (memo[i] != 0)
            return memo[i];

        int ans = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; k++) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            ans = Math.min(ans, dp(j) + costs[k]);
        }

        memo[i] = ans;
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(new MinimumCostForTickets().mincostTickets(
                new int[]{1, 4, 6, 7, 8, 20},
                new int[]{2, 7, 15})
        );
    }
}
