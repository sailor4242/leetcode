package com.dzavorin.solutions.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MaximumAveragePassRatio {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparing(x -> ((double)(x[0]+1)/(x[1]+1) - (double)x[0]/x[1]) * -10000));

        for (int[] clazz : classes) {
            pq.add(clazz);
        }

        while (extraStudents > 0) {
            int[] clazz = pq.poll();
            clazz[0]++;
            clazz[1]++;
            pq.add(clazz);
            extraStudents--;
        }

        double res = 0;
        for (int[] clazz : classes) {
            res += ((double) clazz[0] / clazz[1]);
        }

        return res / classes.length;
    }

    // DP TLE
    public double maxAverageRatio2(int[][] classes, int extraStudents) {
        double totalAverage = 0;
        for (int[] clazz : classes) {
            totalAverage += (double) clazz[0] / clazz[1];
        }

        return dp(classes, extraStudents, totalAverage, new HashMap<>()) / classes.length;
    }

    private double dp(int[][] classes, int extraStudents, double totalAverage, Map<String, Double> memo) {
        if (extraStudents == 0) {
            return totalAverage;
        }

        String key = extraStudents + "_" + ((double) Math.round(totalAverage * 100000d) / 100000d);

        if (memo.containsKey(key)) return memo.get(key);

        double res = 0;
        for (int i = 0; i < classes.length; i++) {
            double avg = ((double) (classes[i][0]) / (classes[i][1]));
            if (avg >= 1.) continue;
            classes[i][0]++;
            classes[i][1]++;
            double nextTotal = totalAverage - avg + ((double) classes[i][0] / classes[i][1]);
            res = Math.max(res, dp(classes, extraStudents - 1, nextTotal, memo));
            classes[i][0]--;
            classes[i][1]--;
        }

        memo.put(key, res);
        return res;
    }
}
