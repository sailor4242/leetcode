package com.dzavorin.solutions.graph.djikstra;

import java.util.*;

public class NumberOfRestrictedPathsFromFirstToLastNode {

    int mod = (int) Math.pow(10, 9) + 7;

    public int countRestrictedPaths(int n, int[][] edges) {

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1],edge[2]});
            graph.get(edge[1]).add(new int[]{edge[0],edge[2]});
        }

        int[] shortest = dijkstra(n, graph);
        return dp(shortest, graph, 1, new Integer[n + 1], n);
    }

    private int dp(int[] shortest, Map<Integer, List<int[]>> graph, int i, Integer[] memo, int n) {
        if (i == n) {
            return 1;
        }

        if (memo[i] != null) return memo[i];

        int res = 0;
        for (int[] edge : graph.get(i)) {
            if (shortest[i] > shortest[edge[0]]) {
                res = (res + dp(shortest, graph, edge[0], memo, n) % mod) % mod;
            }
        }

        return memo[i] = res;
    }

    private int[] dijkstra(int n, Map<Integer, List<int[]>> graph) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[n] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        pq.offer(new int[]{n, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            for (int[] edge : graph.get(cur[0])) {

                if (dist[cur[0]] + edge[1] < dist[edge[0]]) {
                    dist[edge[0]] = dist[cur[0]] + edge[1];
                    pq.add(new int[]{edge[0], dist[edge[0]]});
                }
            }
        }

        return dist;
    }

}
