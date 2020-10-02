package com.dzavorin.solutions.arrays;

public class TeemoAttacking {

    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;
        int nextTime = timeSeries[0] + duration;
        int res = duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] > nextTime) {
                res += duration;
            } else {
                res += timeSeries[i] - timeSeries[i - 1];
            }
            nextTime = timeSeries[i] + duration;
        }
        return res;
    }

    public int findPoisonedDuration2(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;

        int total = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            total += Math.min(timeSeries[i + 1] - timeSeries[i], duration);
        }
        return total + duration;
    }

}
