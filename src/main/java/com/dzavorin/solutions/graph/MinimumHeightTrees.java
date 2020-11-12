package com.dzavorin.solutions.graph;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumHeightTrees {

    // O(n) "top sort style"
    // We can have at most 2 MHT root nodes, so keep on removing leaves until we have 2 or less nodes
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (edges.length == 0 || edges[0].length == 0) {
            return List.of(0);
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int[] edge : edges) {
            map.putIfAbsent(edge[0], new HashSet<>());
            map.get(edge[0]).add(edge[1]);

            map.putIfAbsent(edge[1], new HashSet<>());
            map.get(edge[1]).add(edge[0]);
        }


        Set<Integer> leaves = map.entrySet().stream()
                .filter(entry -> entry.getValue().size() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        while (n > 2) {
            n -= leaves.size();
            Set<Integer> nextLeaves = new HashSet<>();

            for (Integer leaf : leaves) {
                Integer node = map.get(leaf).iterator().next();
                map.get(leaf).remove(node);
                Set<Integer> nodeNeighbours = map.get(node);
                nodeNeighbours.remove(leaf);

                if (nodeNeighbours.size() == 1) {
                    nextLeaves.add(node);
                }
            }

            leaves = nextLeaves;
        }

        return new ArrayList<>(leaves);
    }

    ////// With DFS, find the longest path of the Tree.
    //  Return the middle points of the longest path.

    int diameter;
    int nodeIndex;
    List<Integer> res;

    public List<Integer> findMinHeightTrees3(int n, int[][] edges) {

        Map<Integer, List<Integer>> tree = new HashMap<>();
        tree.put(0, new ArrayList<>());
        for (int i = 1; i < n; i++)
            tree.put(i, new ArrayList<>());
        for (int[] x : edges) {
            tree.get(x[0]).add(x[1]);
            tree.get(x[1]).add(x[0]);
        }
        diameter = -1;
        nodeIndex = 0;

        dfs(tree, 0, 0, new HashSet<>(), new ArrayList<>());

        diameter = -1;
        dfs(tree, nodeIndex, 0, new HashSet<>(), new ArrayList<>());

        List<Integer> ans = new ArrayList<>();
        ans.add(res.get(diameter / 2));
        if (diameter % 2 == 1)
            ans.add(res.get(diameter / 2 + diameter % 2));

        return ans;
    }

    public void dfs(Map<Integer, List<Integer>> tree, int node, int level, Set<Integer> visited, List<Integer> ans) {
        visited.add(node);
        ans.add(node);

        if (level > diameter) {
            diameter = level;
            nodeIndex = node;
            res = ans;
        }

        for (int nextNode : tree.get(node)) {
            if (!visited.contains(nextNode))
                dfs(tree, nextNode, level + 1, visited, new ArrayList<>(ans));
        }
    }

    ///// brute-force O(n^2) TLE
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        if (edges.length == 0 || edges[0].length == 0) {
            return List.of(0);
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] edge : edges) {
            map.putIfAbsent(edge[0], new LinkedList<>());
            map.get(edge[0]).add(edge[1]);

            map.putIfAbsent(edge[1], new LinkedList<>());
            map.get(edge[1]).add(edge[0]);
        }

        List<Integer> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int[] curRes = bfsHeight(i, map);
            if (curRes[1] < min) {
                min = curRes[1];
                res = new ArrayList<>();
                res.add(curRes[0]);
            } else if (curRes[1] == min) {
                res.add(curRes[0]);
            }
        }

        return res;
    }

    public int[] bfsHeight(int i, Map<Integer, List<Integer>> map) {

        int res = 0;

        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, 0});

        Set<Integer> visited = new HashSet<>();
        visited.add(i);

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            List<Integer> curEdges = map.get(cur[0]);

            res = Math.max(res, cur[1]);

            for (Integer edge : curEdges) {
                if (!visited.contains(edge)) {
                    queue.add(new int[]{edge, 1 + cur[1]});
                    visited.add(edge);
                }
            }
        }

        return new int[]{i, res};
    }

    public static void main(String[] args) {
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}}));
    }
}
