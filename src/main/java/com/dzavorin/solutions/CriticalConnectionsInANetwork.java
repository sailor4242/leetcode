package com.dzavorin.solutions;

import java.util.*;

public class CriticalConnectionsInANetwork {

    Map<Integer, List<Integer>> graph = new HashMap<>();
    Map<Integer, Integer> timeMap = new HashMap<>();
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        for (List<Integer> connection : connections) {
            graph.putIfAbsent(connection.get(0), new ArrayList<>());
            graph.get(connection.get(0)).add(connection.get(1));
            graph.putIfAbsent(connection.get(1), new ArrayList<>());
            graph.get(connection.get(1)).add(connection.get(0));
        }

        helper(-1, 0, 0, new HashSet<>());
        return result;
    }

    private void helper(int parent, int node, int time, Set<Integer> visited) {
        visited.add(node);
        timeMap.put(node, time);
        int currTime = time;
        time++;

        for (int neighbour : graph.get(node)) {
            if (neighbour == parent) {
                continue;
            }

            if (!visited.contains(neighbour)) {
                helper(node, neighbour, time, visited);
            }

            timeMap.put(node, Math.min(timeMap.get(node), timeMap.get(neighbour)));
            if (currTime < timeMap.get(neighbour)) {
                result.add(Arrays.asList(node, neighbour));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new CriticalConnectionsInANetwork().criticalConnections(
                5, List.of(List.of(1, 0), List.of(2, 0), List.of(3, 2),
                        List.of(4, 2), List.of(4, 3), List.of(3, 0), List.of(4, 0)
                )));
    }
}
