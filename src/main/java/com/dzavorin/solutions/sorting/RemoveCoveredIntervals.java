package com.dzavorin.solutions.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {

        if (intervals.length < 2) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.<int[], Integer>comparing(a -> a[0])
                .thenComparing(a -> a[0] - a[1]));

        int res = intervals.length;
        int[] prev = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (prev[0] <= cur[0] && prev[1] >= cur[1]) {
                res--;
            } else {
                prev = cur;
            }
        }

        return res;
    }

}
