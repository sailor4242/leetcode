package com.dzavorin.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {

        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, Comparator.comparing(arr -> arr[0]));

        for (int i = 0; i < intervals.length; i++) {

            int n = intervals[i][1];
            int[] cur = new int[]{intervals[i][0], -1};
            int j = i + 1;
            while (j < intervals.length) {

                if (n >= intervals[j][0] && n < intervals[j][1]) {
                    n = intervals[j][1];
                } else if (n < intervals[j][0]){
                    cur[1] = n;
                    res.add(cur);
                    break;
                }
                j++;
            }

            if (cur[1] == -1) {
                cur[1] = n;
                res.add(cur);
            }
            i = j - 1;

        }

        return res.toArray(new int[res.size()][]);
    }

    public int[][] merge2(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Sort by ascending starting point
        Arrays.sort(intervals, Comparator.comparing(arr -> arr[0]));

        List<int[]> result = new ArrayList<>();
        int[] cur = intervals[0];
        result.add(cur);
        for (int[] interval : intervals) {
            if (interval[0] <= cur[1]) // Overlapping intervals, move the end if needed
                cur[1] = Math.max(cur[1], interval[1]);
            else {                     // Disjoint intervals, add the new interval to the list
                cur = interval;
                result.add(cur);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new MergeIntervals().merge(
                new int[][]{{1,3},{2,6},{8,10},{15,18}}
        )));

        System.out.println(Arrays.deepToString(new MergeIntervals().merge(
                new int[][]{{1,4},{4,5}}
        )));

        System.out.println(Arrays.deepToString(new MergeIntervals().merge(
                new int[][]{{1,4},{0,4}}
        )));

        System.out.println(Arrays.deepToString(new MergeIntervals().merge(
                new int[][]{{1,4},{2,3}}
        )));

        System.out.println(Arrays.deepToString(new MergeIntervals().merge(
                new int[][]{{3,3},{0,1},{0,0}}
        )));
    }

}
