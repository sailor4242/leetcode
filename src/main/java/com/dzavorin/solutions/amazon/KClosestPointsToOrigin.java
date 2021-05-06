package com.dzavorin.solutions.amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparing(arr -> arr[1]));
        for (int i = 0; i < points.length; i++) {
            pq.add(new double[]{i, calcDistance(points[i])});
        }
        int[][] res = new int[k][2];
        int i = 0;
        while (!pq.isEmpty() && i < k) {
            res[i++] = points[(int) pq.poll()[0]];
        }
        return res;
    }

    private double calcDistance(int[] point) {
        return Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
    }
}
