package com.dzavorin.solutions.greedy;

import java.util.*;

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {

        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < trips.length; i++) {
            map.put(trips[i][1], map.getOrDefault(trips[i][1], 0) + trips[i][0]);
            map.put(trips[i][2], map.getOrDefault(trips[i][2], 0) - trips[i][0]);
            max = Math.max(trips[i][2], max);
        }

        for (int i = 0; i <= max; i++) {
            capacity -= map.getOrDefault(i, 0);
            if (capacity < 0) return false;
        }

        return true;
    }

    public boolean carPooling2(int[][] trips, int capacity) {
        int passengers = 0;
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int[] trip : trips) {
            passengers += trip[0];
            while (!pq.isEmpty() && pq.peek()[2] <= trip[1]) {
                passengers -= pq.poll()[0];
            }
            if (passengers > capacity) return false;
            pq.offer(trip);
        }
        return true;
    }
}
