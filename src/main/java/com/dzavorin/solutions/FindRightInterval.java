package com.dzavorin.solutions;

import java.util.TreeMap;

public class FindRightInterval {

    public int[] findRightInterval(int[][] intervals) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int i = 0;
        for (int[] interval: intervals) {
            map.put(interval[0], i++);
        }
        int[] res = new int[intervals.length];
        i = 0;
        for (int[] interval : intervals) {
            Integer celV = map.get(interval[1]);
            if (celV == null) {
                var cel = map.higherEntry(interval[1]);
                celV = cel == null ? null : cel.getValue();
            }

            res[i++] = celV == null ? -1 : celV;
        }

        return res;
    }

}
