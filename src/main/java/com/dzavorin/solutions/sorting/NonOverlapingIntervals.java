package com.dzavorin.solutions.sorting;

import java.util.Arrays;

public class NonOverlapingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int res = 0;
        int prev = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prev) {
                res++;
            } else {
                prev = intervals[i][1];
            }
        }

        return res;
    }

}
