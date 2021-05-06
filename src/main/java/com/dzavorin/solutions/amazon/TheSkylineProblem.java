package com.dzavorin.solutions.amazon;

import java.util.*;

public class TheSkylineProblem {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            height.add(new int[]{b[0], b[2]});
            height.add(new int[]{b[1], -b[2]});
        }
        height.sort((a, b) -> {
            if (a[0] != b[0])
                return a[0] - b[0];
            return b[1] - a[1];
        });
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.offer(0);
        int prev = 0;
        for (int[] h : height) {
            if (h[1] > 0) {
                pq.offer(h[1]);
            } else {
                pq.remove(-h[1]);
            }
            int cur = pq.peek();
            if (prev != cur) {
                result.add(List.of(h[0], cur));
                prev = cur;
            }
        }
        return result;
    }
}
