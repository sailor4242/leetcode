package com.dzavorin.solutions.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CriticalConnectionsInANetwork {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (List<Integer> connection : connections) {
            graph.putIfAbsent(connection.get(0), new ArrayList<>());
            graph.get(connection.get(0)).add(connection.get(1));
            graph.putIfAbsent(connection.get(1), new ArrayList<>());
            graph.get(connection.get(1)).add(connection.get(0));
        }

        boolean[] visited = new boolean[n];
        List<List<Integer>> res = new ArrayList<>();
        Integer[] low = new Integer[n];

        dfs(0, 0, 0, graph, visited, low, res);
        return res;
    }

    private void dfs(int cur, int prev, int time,
                     Map<Integer, List<Integer>> graph,
                     boolean[] visited,
                     Integer[] low,
                     List<List<Integer>> res) {

        visited[cur] = true;
        low[cur] = time++;

        int curTime = low[cur];

        for (int next : graph.get(cur)) {
            if (next == prev) {
                continue;
            }
            if (!visited[next]) {
                dfs(next, cur, time, graph, visited, low, res);
            }

            low[cur] = Math.min(low[cur], low[next]);

            if (curTime < low[next]) {
                res.add(List.of(cur, next));
            }
        }

    }

}
