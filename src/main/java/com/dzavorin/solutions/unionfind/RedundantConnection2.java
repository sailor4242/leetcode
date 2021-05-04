package com.dzavorin.solutions.unionfind;

public class RedundantConnection2 {

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = 1;
        for (int[] edge : edges) {
            n = Math.max(n, edge[0]);
            n = Math.max(n, edge[1]);
        }

        UF uf = new UF(n + 1);

        for (int[] edge : edges) {
            uf.union(edge);
        }

        if (uf.candidate1 == null) {
            return uf.candidate2;
        } else if (uf.candidate2 == null) {
            return uf.candidate1;
        } else {

            for (int[] edge : edges) {
                if (edge[1] == uf.candidate2[1]) {
                    return edge;
                }
            }

        }

        return new int[]{};
    }

    static class UF {
        int[] arr;
        int[] candidate1 = null; // if cycle
        int[] candidate2 = null; // if 2 parents

        UF(int n) {
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i;
            }
        }

        void union(int[] edge) {
            int x = find(edge[0]);
            int y = find(edge[1]);

            if (x == y) { // cycle
                candidate1 = edge;
            } else if (y != edge[1]) { // means edge has more than one parent (first one is edge[0])
                candidate2 = edge;
            } else {
                arr[y] = x;
            }

        }

        int find(int a) {
            if (arr[a] != a) {
                arr[a] = find(arr[a]);
            }
            return arr[a];
        }
    }

}
