package com.dzavorin.solutions.sorting;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {

        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Double.compare(p2.val, p1.val));

        for (int i = 0; i < points.length; i++) {
            double val = Math.sqrt(Math.pow(points[i][0], 2) + Math.pow(points[i][1], 2));
            if (pq.size() < K) {
                pq.add(new Pair(val, i));
            } else if (pq.size() == K && val < pq.peek().val) {
                pq.poll();
                pq.add(new Pair(val, i));
            }

        }

        int[][] res = new int[K][2];
        int j = 0;
        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            res[j++] = new int[]{points[p.i][0], points[p.i][1]};
        }

        return res;
    }

    static class Pair {
        public int i;
        public double val;

        Pair(double val, int i) {
            this.i = i;
            this.val = val;
        }
    }
}
