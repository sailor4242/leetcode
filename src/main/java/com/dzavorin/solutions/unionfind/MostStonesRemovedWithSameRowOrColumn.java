package com.dzavorin.solutions.unionfind;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostStonesRemovedWithSameRowOrColumn {


    ///// uf

    public int removeStones(int[][] stones) {

        int n = stones.length;
        if (n == 0) return 0;

        int[] parent = new int[20000];

        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int res = 0;
        for (int[] stone : stones) {
            int x = find(stone[0], parent);
            int y = find(stone[1] + 10000, parent);

            if (x != y) {
                parent[y] = x;
            }
        }

        Set<Integer> seen = new HashSet<>();
        for (int[] stone : stones)
            seen.add(find(stone[0], parent));

        return n - seen.size();

    }

    int find(int x, int[] parent) {
        if (x != parent[x]) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }


    /////// dfs

    private int row;
    private int col;
    private int sum;
    private boolean[][] visited;

    public int removeStones2(int[][] stones) {
        if (stones == null || stones.length == 0)
            return 0;

        row = stones.length;
        col = stones[0].length;
        visited = new boolean[10001][10001];

        for (int[] stone : stones) {
            int x = stone[0];
            int y = stone[1];

            if (!visited[x][y]) {
                sum += dfs(x, y, stones) - 1;
            }
        }
        return sum;
    }

    private int dfs(int i, int j, int[][] stones) {
        visited[i][j] = true;
        int res = 0;
        for (int[] stone : stones) {
            int newx = stone[0];
            int newy = stone[1];
            if (!visited[newx][newy] && (newx == i || newy == j)) {
                res += dfs(newx, newy, stones);
            }
        }
        return 1 + res;
    }


    //// uf 2

    public int removeStones3(int[][] stones) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        UnionFind uf = new UnionFind(stones.length);
        for(int i = 0; i < stones.length; i++) {
            int[] s1 = stones[i];
            for(int j = 0; j < stones.length; j++) {
                if(i != j) {
                    int[] s2 = stones[j];
                    if(s1[0] == s2[0] || s1[1] == s2[1]) { //if row or col is same
                        uf.union(i, j);
                    }
                }
            }
        }

        for(int i = 0; i < stones.length; i++) {
            int root = uf.find(i);
            int c = map.getOrDefault(root, 0);
            c++;
            map.put(root, c);
        }
        int components = map.size();
        return stones.length - components;
    }

    private class UnionFind {
        int[] sz;
        int[] id;

        UnionFind(int size) {
            sz = new int[size];
            id = new int[size];
            for(int i = 0; i < sz.length; i++) {
                sz[i] = 1;
                id[i] = i;
            }
        }

        private int find(int p) {
            int root = p;
            while(root != id[root]) {
                root = id[root];
            }
            return root;
        }

        private void union(int p, int q) {
            int r1 = find(p);
            int r2 = find(q);
            if(r1 == r2) {
                return;
            }

            if(sz[r1] < sz[r2]) {
                id[r1] = r2;
                sz[r2] += sz[r1];
            } else {
                id[r2] = r1;
                sz[r1] += sz[r2];
            }
        }
    }

}
