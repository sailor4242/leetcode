package com.dzavorin.solutions.graph.djikstra;

import java.util.*;

public class FindCityWithTheSmallestNumberOfNeighborsAtATresholdDistance {

    //Dijkstra

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});

            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        int min = Integer.MAX_VALUE;
        int res = -1;

        for (int i = 0; i < n; i++) {
            if (!graph.containsKey(i)) {
                res = Math.max(res, i);
            }
        }

        if (res != -1) return res;

        for (int i = 0; i < n; i++) {

            Map<Integer, Integer> shortest = dijkstra(n, graph, i, distanceThreshold);
            System.out.println("" + i + "-" + (shortest.keySet().size() - 1));
            if (shortest.keySet().size() - 1 <= min) {
                min = shortest.keySet().size() - 1;
                res = Math.max(res, i);
            }
        }

        return res;
    }

    // O(N*logN)
    private Map<Integer, Integer> dijkstra(int n, Map<Integer, List<int[]>> graph, int v, int distanceThreshold) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[v] = 0;
        boolean[] visited = new boolean[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(pair -> pair[1]));
        pq.add(new int[]{v, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            visited[cur[0]] = true;
            List<int[]> edges = graph.get(cur[0]);
            for (int[] edge : edges) {
                if (visited[edge[0]]) continue;

                if (edge[1] + dist[cur[0]] < dist[edge[0]]) {
                    dist[edge[0]] = edge[1] + dist[cur[0]];
                    pq.add(new int[]{edge[0], dist[edge[0]]});
                }
            }
        }

        Map<Integer, Integer> res = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (dist[i] <= distanceThreshold) {
                res.put(i, dist[i]);
            }
        }

        return res;
    }


    /////Floyd-marshal

    public int findTheCity2(int n, int[][] edges, int distanceThreshold) {

        //****** Creating a Adj Matrix to from given edges
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE / 2);     //Devideing to prevent overflow
            graph[i][i] = 0;    //Diagnoally fill 0 because we distance to node itself is always 0.
        }

        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = Math.min(graph[edge[0]][edge[1]], edge[2]);
            graph[edge[1]][edge[0]] = Math.min(graph[edge[1]][edge[0]], edge[2]);
        }

        //****** All Pairs Shortest Path Algorithm (Floyd Warshall Algo)****
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        //****** Couting the result, finding a node where we can reach minimum numbers as neighbors and then getting maximum index as they want maximum number of the node.
        int result = -1;
        int minRechable = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int reachable = 0;
            for (int j = 0; j < n; j++) {
                if (graph[i][j] <= distanceThreshold) {
                    reachable++;
                }
            }
            if (minRechable >= reachable) {
                minRechable = reachable;
                result = i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        new FindCityWithTheSmallestNumberOfNeighborsAtATresholdDistance().findTheCity(4,
//                new int[][]{{0,1,3}, {1,2,1}, {1,3,4}, {2,3,1}}, 4);

        new FindCityWithTheSmallestNumberOfNeighborsAtATresholdDistance().findTheCity(6,
                new int[][]{{0, 3, 7}, {2, 4, 1}, {0, 1, 5}, {2, 3, 10}, {1, 3, 6}, {1, 2, 1}}, 417);
    }
}
