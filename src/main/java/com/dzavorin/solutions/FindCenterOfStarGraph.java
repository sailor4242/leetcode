package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class FindCenterOfStarGraph {

    public int findCenter(int[][] edges) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int[] edge : edges) {
            counts.put(edge[0], counts.getOrDefault(edge[0], 0) + 1);
            counts.put(edge[1], counts.getOrDefault(edge[1], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == counts.size() - 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

}
