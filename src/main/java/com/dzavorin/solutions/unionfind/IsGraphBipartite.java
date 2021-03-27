package com.dzavorin.solutions.unionfind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class IsGraphBipartite {

    public boolean isBipartite(int[][] graph) {
        Set<Integer> blue = new HashSet<>(); // Since I want two sets .. blue and red are colors/
        Set<Integer> red = new HashSet<>();
        Set<Integer> visited = new HashSet<>();

        for (int node = 0; node < graph.length; node++) {
            if (visited.contains(node)) continue;

            LinkedList<Integer> list = new LinkedList<>();
            list.add(node);
            blue.add(node); // Initial node color assumed to be blue.. before starting BFS
            while (!list.isEmpty()) {
                int n = list.poll();
                visited.add(n);

                int[] adj = graph[n];
                for (int neighbour : adj) {
                    if (red.contains(n)) {
                        if (red.contains(neighbour)) return false;
                        blue.add(neighbour);
                    } else if (blue.contains(n)) {
                        if (blue.contains(neighbour)) return false;
                        red.add(neighbour);
                    }
                    if (!visited.contains(neighbour))
                        list.offer(neighbour);
                }
            }
        }

        return true;
    }


    // union find

    public boolean isBipartite2(int[][] graph) {
        int[] parent = new int[graph.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < graph.length; i++) {
            int min = Integer.MAX_VALUE;
            int[] adj = graph[i];
            for (int j : adj) {
                if (find(parent, i) == find(parent, j)) {
                    return false;
                }
                min = Math.min(min, j);
            }
            for (int j : adj) {
                parent[j] = min;
            }
        }
        return true;
    }

    private int find(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
        }
        return parent[i];
    }

    public static void main(String[] args) {
//        System.out.println(new IsGraphBipartite().isBipartite(new int[][]{{1}, {0, 3}, {3}, {1, 2}}));
        System.out.println(new IsGraphBipartite().isBipartite2(new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}}));
//        System.out.println(new IsGraphBipartite().isBipartite(new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}}));
    }
}
