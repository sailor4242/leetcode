package com.dzavorin.solutions.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfArrowsToBurstBalloons {

    public int findMinArrowShots(int[][] points) {
        if (points.length < 2) return points.length;

        Arrays.sort(points, Comparator.<int[], Integer>comparing(b -> b[0])
                .thenComparing(b -> b[1]));

        int[] cur = new int[]{points[0][0], points[0][1]};
        int res = 1;
        for (int i = 1; i < points.length; i++) {
            if (cur[1] >= points[i][0] && cur[0] <= points[i][0]) {
                cur[1] = Math.min(cur[1], points[i][1]);
                cur[0] = Math.max(cur[0], points[i][0]);
            } else {
                cur[0] = points[i][0];
                cur[1] = points[i][1];
                res++;
            }
        }

        return res;
    }


    //same LC solution

    public int findMinArrowShots2(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int arrow = 1;
        int end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > end) {
                arrow++;
                end = points[i][1];
            } else {
                end = Math.min(end, points[i][1]);
            }
        }
        return arrow;
    }

}
