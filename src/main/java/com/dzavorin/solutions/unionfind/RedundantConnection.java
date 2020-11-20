package com.dzavorin.solutions.unionfind;

public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {
        int n = 1;
        for (int[] edge : edges) {
            n = Math.max(n, edge[0]);
            n = Math.max(n, edge[1]);
        }

        UF uf = new UF(n + 1);
        int[] res = new int[]{-1, -1};
        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) {
                res = edge;
            }
        }

        return res;
    }

    static class UF {
        int n;
        int[] parent;
        int[] rank;

        UF(int n) {
            this.n = n;
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int p) {
            while (parent[p] != p) {
                p = parent[p] = parent[parent[p]];
            }
            return p;
        }

        boolean union(int p, int q) {
            int parp = find(p);
            int parq = find(q);

            if (parp == parq)
                return true;

            if (rank[parq] > rank[parp]) {
                parent[parp] = parq;
            } else {
                parent[parq] = parp;
                if (rank[parp] == rank[parq]) {
                    rank[parp]++;
                }
            }

            n--;
            return false;
        }

    }


    /// short version

    static class Solution2 {
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            int[] parent = new int[n + 1];

            for (int i = 0; i < n; ++i)
                parent[i] = i;

            int[] res = new int[2];
            for (int i = 0; i < n; ++i) {
                int x = find(edges[i][0], parent);
                int y = find(edges[i][1], parent);

                if (x != y) {
                    parent[y] = x;
                } else {
                    res[0] = edges[i][0];
                    res[1] = edges[i][1];
                }
            }
            return res;
        }

        private int find(int x, int[] parent) {
            return parent[x] == x ? x : find(parent[x], parent);
        }
    }

}
