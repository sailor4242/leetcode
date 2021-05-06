package com.dzavorin.solutions.amazon;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {

    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int stick : sticks) {
            pq.add(stick);
        }
        Integer cost = 0;
        while (pq.size() != 1) {
            Integer one = pq.poll();
            Integer two = pq.poll();
            int sum = one + two;
            pq.add(sum);
            cost += sum;
        }

        return cost;
    }
}
