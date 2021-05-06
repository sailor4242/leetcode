package com.dzavorin.solutions.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0 || intervals[0].length == 0) return new int[][]{{}};

        Arrays.sort(intervals, Comparator.<int[], Integer>comparing(interval -> interval[0]));


        List<int[]> res = new ArrayList<>();
        int lo = intervals[0][0];
        int hi = intervals[0][1];
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];

            if (interval[0] > hi) {
                res.add(new int[]{lo, hi});
                lo = interval[0];
                hi = interval[1];
            } else {
                hi = Math.max(hi, interval[1]);
            }
        }
        res.add(new int[]{lo, hi});

        int[][] resArr = new int[res.size()][2];
        int i = 0;
        for (int[] interval : res) {
            resArr[i++] = interval;
        }
        return resArr;
    }

}
