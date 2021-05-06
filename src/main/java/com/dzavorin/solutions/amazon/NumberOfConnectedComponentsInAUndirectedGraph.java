package com.dzavorin.solutions.amazon;

import java.util.*;

public class NumberOfConnectedComponentsInAUndirectedGraph {

    public int countComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            count++;
            LinkedList<Integer> list = new LinkedList<>();
            list.add(i);
            while (!list.isEmpty()) {
                Integer cur = list.removeFirst();
                for (Integer next : graph.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        list.add(next);
                    }
                }
            }

        }

        return count;
    }

}
