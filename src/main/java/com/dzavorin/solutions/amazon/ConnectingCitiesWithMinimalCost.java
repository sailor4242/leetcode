package com.dzavorin.solutions.amazon;

import java.util.*;

public class ConnectingCitiesWithMinimalCost {

    public int minimumCost(int N, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] con : connections) {
            graph.putIfAbsent(con[0], new ArrayList<>());
            graph.putIfAbsent(con[1], new ArrayList<>());

            graph.get(con[0]).add(new int[]{con[1], con[2]});
            graph.get(con[1]).add(new int[]{con[0], con[2]});
        }

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(arr -> arr[1]));
        pq.add(new int[]{1, 0});
        int cost = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visited.contains(cur[0])) continue;

            visited.add(cur[0]);
            cost += cur[1];
            for (int[] next : graph.get(cur[0])) {
                pq.offer(next);
            }
        }

        return visited.size() == N ? cost : -1;
    }

    ///


    int[] parent;
    int n;

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[px] = py;
            n--;
        }
    }

    private int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    public int minimumCost2(int N, int[][] connections) {
        parent = new int[N + 1];
        n = N;
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        Arrays.sort(connections, Comparator.comparingInt(a -> a[2]));

        int res = 0;

        for (int[] c : connections) {
            int x = c[0];
            int y = c[1];
            if (find(x) != find(y)) {
                res += c[2];
                union(x, y);
            }
        }

        return n == 1 ? res : -1;
    }

}
