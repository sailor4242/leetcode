package com.dzavorin.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertIntervals {

    public int[][] insert (int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList <>();

        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                res.add (interval);
            } else if (interval[0] > newInterval[1]) {
                res.add (newInterval);
                newInterval = interval;
            } else {
                newInterval[0] = Math.min (interval[0], newInterval[0]);
                newInterval[1] = Math.max (interval[1], newInterval[1]);
            }
        }

        res.add (newInterval);

        return res.toArray (new int[res.size ()][]);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {

        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }

        int lo = newInterval[0];
        int hi = newInterval[1];
        int[] ins = new int[2];

        List<int[]> res = new ArrayList<>();

        boolean finished = false;
        for (int i = 0; i < intervals.length; i++) {
            int[] cur = intervals[i];

            if (finished) {
                res.add(cur);
            } else if (lo < cur[0]) {
                ins[0] = lo;
                i = findHi(intervals, hi, ins, i, res) - 1;
                finished = true;
            } else if (lo >= cur[0] && lo <= cur[1]) {
                ins[0] = cur[0];
                i = findHi(intervals, hi, ins, i, res) - 1;
                finished = true;
            } else if (i == intervals.length - 1) {
                res.add(cur);
                res.add(newInterval);
            } else {
                res.add(cur);
            }
        }

        int[][] resArr = new int[res.size()][2];
        int i = 0;
        for (int[] it : res) {
            resArr[i++] = it;
        }

        return resArr;
    }

    private int findHi(int[][] intervals, int hi, int[] ins, int i, List<int[]> res) {

        for (int j = i; j < intervals.length; j++) {
            if (hi < intervals[j][0]) {
                ins[1] = hi;
                res.add(ins);
                return j;
            } else if (hi >= intervals[j][0] && hi <= intervals[j][1]) {
                ins[1] = intervals[j][1];
                res.add(ins);
                return j + 1;
            } else if (j == intervals.length - 1) {
                ins[1] = hi;
                res.add(ins);
                return j + 1;
            }
        }
        return intervals.length - 1;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{2, 3}, {6, 9}}, new int[]{0, 1}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{2, 3}, {6, 9}}, new int[]{10, 11}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{2, 4}, {6, 9}}, new int[]{3, 7}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{2, 4}, {6, 9}}, new int[]{1, 12}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{}, new int[]{5, 7}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{1, 5}}, new int[]{5, 7}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{1, 3}}, new int[]{5, 7}
        )));

        System.out.println(Arrays.deepToString(new InsertIntervals().insert(
                new int[][]{{4, 5}}, new int[]{1, 2}
        )));

    }
}
