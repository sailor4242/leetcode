package com.dzavorin.solutions.graph.djikstra;

import java.util.*;

public class NetworkDelayTime {

    // Dijkstra
    public int networkDelayTime(int[][] times, int N, int K) {

        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] edges : times) {
            map.putIfAbsent(edges[0], new ArrayList<>());
            map.get(edges[0]).add(new int[]{edges[1], edges[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[1]));
        pq.add(new int[]{K, 0});

        Map<Integer, Integer> timesMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            timesMap.put(i, Integer.MAX_VALUE);
        }
        timesMap.put(K, 0);

        Set<Integer> visited = new HashSet<>();

        while (!pq.isEmpty()) {

            int[] arr = pq.poll();
            int cur = arr[0];

            visited.add(cur);

            if (map.containsKey(cur)) {
                for (int[] narr : map.get(cur)) {
                    if (visited.contains(narr[0])) continue;
                    int nextTime = timesMap.get(cur) + narr[1];

                    int prevTime = timesMap.get(narr[0]);

                    if (nextTime < prevTime) {
                        timesMap.put(narr[0], nextTime);
                        pq.add(new int[]{narr[0], nextTime});
                    }
                }
            }
        }

        var maxOpt = timesMap.values().stream().max(Comparator.naturalOrder());

        if (maxOpt.isEmpty()) {
            return -1;
        }
        int max = maxOpt.get();

        return max == Integer.MAX_VALUE ? -1 : max;
    }

    public static void main(String[] args) {
        System.out.println(new NetworkDelayTime().networkDelayTime(
                new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2
        )); // 2

        System.out.println(new NetworkDelayTime().networkDelayTime(
                new int[][]{{1, 2, 1}}, 2, 2
        )); // -1

        System.out.println(new NetworkDelayTime().networkDelayTime(
                new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 1}}, 3, 2
        )); // -1

    }

}
