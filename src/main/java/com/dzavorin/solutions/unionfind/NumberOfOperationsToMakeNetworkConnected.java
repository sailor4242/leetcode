package com.dzavorin.solutions.unionfind;

import java.util.*;

public class NumberOfOperationsToMakeNetworkConnected {

    //// dfs

    public int makeConnected2(int n, int[][] connections) {
        if (n > connections.length + 1)
            return -1;

    Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
        graph.put(i, new LinkedList<>());
    }

        for (int[] connection : connections) {
        graph.get(connection[0]).add(connection[1]);
        graph.get(connection[1]).add(connection[0]);
    }
    int ans = 0;

    boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
        if (!visited[i]) {
            dfs(i, graph, visited);
            ans++;
        }
    }

        return ans - 1;
    }

    public void dfs(int i, Map<Integer, List<Integer>> graph, boolean[] visited) {
        visited[i] = true;

        for (int n : graph.get(i)) {
            if (!visited[n])
                dfs(n, graph, visited);
        }

    }

    //////// union find

    public int makeConnected(int n, int[][] connections) {
        UnionFind uf = new UnionFind(n);
        for (int[] c : connections)
            uf.union(c[0], c[1]);

        return connections.length >= n - 1 ? uf.numComponents - 1 : -1;
    }

    private static class UnionFind {
        private int size;
        private int numComponents; // number of connected components
        private int[] parent;
        private int[] rank;

        public UnionFind(int n) {
            if (n <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");
            size = numComponents = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);

            // These elements are already in the same group!
            if (rootP == rootQ)
                return;

            // Merge smaller component/set into the larger one.
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            } else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }

            // Since the roots found are different we know that the number of components/sets has decreased by one
            numComponents--;
        }

        // Return whether or not the elements 'p' and 'q' are in the same components/set.
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        // Returns the number of remaining components/sets
        public int components() {
            return numComponents;
        }

        // Return the number of elements in this UnionFind/Disjoint set
        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfOperationsToMakeNetworkConnected().makeConnected(12,
                new int[][]{{1, 5}, {1, 7}, {1, 2}, {1, 4}, {3, 7}, {4, 7}, {3, 5}, {0, 6}, {0, 1}, {0, 4}, {2, 6}, {0, 3}, {0, 2}}));
    }
}
