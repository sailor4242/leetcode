package com.dzavorin.solutions.tree;

import java.util.*;

public class SumOfDistancesInTree {

    public int[] sumOfDistancesInTree(int N, int[][] edges) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] edge : edges) {
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            map.putIfAbsent(edge[1], new ArrayList<>());
            map.get(edge[1]).add(edge[0]);
        }


        int[] res = new int[N];

        for (int i = 0; i < N; i++) {

            List<Integer> eds = map.get(i);
            if (map.get(i) == null) {
                continue;
            }

            int[] sums = new int[N];

            boolean[] visited = new boolean[N];
            LinkedList<int[]> list = new LinkedList<>();
            list.add(new int[]{i,0});
            visited[i] = true;

            while (!list.isEmpty()) {
                int[] cur = list.removeFirst();
                sums[cur[0]] = cur[1];

                List<Integer> nextList = map.get(cur[0]);

                for (int n : nextList) {
                    if (!visited[n]) {
                        list.add(new int[]{n, cur[1] + 1});
                        visited[n] = true;
                    }
                }
            }
            int sum = 0;
            for (int s : sums) {
                sum += s;
            }
            res[i] = sum;
        }

        return res;
    }
    //[0,1],[0,2],[2,3],[2,4],[2,5]
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new SumOfDistancesInTree()
                .sumOfDistancesInTree(6, new int[][]{{0,1}, {0,2}, {2,3}, {2,4}, {2, 5}})));
        // 8,12,6,10,10,10

    }
}
