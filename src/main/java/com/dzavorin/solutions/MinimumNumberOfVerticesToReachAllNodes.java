package com.dzavorin.solutions;

import java.util.*;

public class MinimumNumberOfVerticesToReachAllNodes {

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] degrees = new int[n];
        List<Integer> roots = new ArrayList<>();

        for (List<Integer> edge : edges) {
            int to = edge.get(1);
            degrees[to]++;
        }

        for (int i = 0; i < n; i++) {
            if (degrees[i] == 0)
                roots.add(i);
        }

        return roots;
    }

    // topsort like approach because i can

    public List<Integer> findSmallestSetOfVertices2(int n, List<List<Integer>> edges) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        boolean[] leaves = new boolean[n];
        for (List<Integer> edge : edges) {
            map.putIfAbsent(edge.get(1), new ArrayList<>());
            map.get(edge.get(1)).add(edge.get(0));
            leaves[edge.get(0)] = true;
        }

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (!leaves[i]) {
                list.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[n];
        while (!list.isEmpty()) {
            int leaf = list.removeLast();
            if (visited[leaf]) {
                continue;
            }

            visited[leaf] = true;
            List<Integer> next = map.get(leaf);

            if (next == null || next.isEmpty()) {
                res.add(leaf);
            } else {
                for (Integer nextLeaf : next) {
                    if (!visited[nextLeaf]) {
                        list.add(nextLeaf);
                    }
                }
            }

        }

        return res;
    }

}
