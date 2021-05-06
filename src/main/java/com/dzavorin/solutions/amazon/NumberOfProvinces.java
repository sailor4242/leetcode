package com.dzavorin.solutions.amazon;

import java.util.HashSet;
import java.util.Set;

public class NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        if (isConnected.length == 0) {
            return 0;
        }

        int[] parent = new int[isConnected.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[0].length; j++) {
                if (isConnected[i][j] == 1) {
                    union(i, j, parent);
                }
            }
        }

        Set<Integer> provinces = new HashSet<>();
        for (int i = 0; i < isConnected.length; i++) {
            provinces.add(find(i, parent));
        }
        return provinces.size();
    }

    private void union(int x, int y, int[] parent) {
        parent[find(x, parent)] = find(y, parent);
    }

    private int find(int p, int[] parent) {
        if (p != parent[p]) {
            parent[p] = find(parent[p], parent);
        }
        return parent[p];
    }

    /////

    public int findCircleNum2(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }

}
