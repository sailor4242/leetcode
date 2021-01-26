package com.dzavorin.solutions.unionfind;

import java.util.HashMap;
import java.util.Map;

public class MinimizeHammingDistanceAfterSwapOperations {

    /**
     The detail of how it swaps does not matter
     If we can swap [0, 1] and [1, 2], then we can rearrange [0, 1, 2] to everything we want.
     Similar question: 1202. Smallest String With Swaps
     Thus, union-find would be a good fit
     Steps:
     Union-find all connected indexes
     Count elements for each union-find group (we use Map<Integer, Map<Integer, Integer>> here)
     Scan the target, try to find the element in that union-find group greedily
        if found: count - 1
        if not: diff + 1
     */
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {

        int N = source.length;

        int[] uf = new int[N];
        for (int i = 0; i < N; i++) {
            uf[i] = i;
        }

        // union-find
        for (int[] swap : allowedSwaps) {

            int parentA = find(uf, swap[0]);
            int parentB = find(uf, swap[1]);

            if (parentA != parentB) {
                uf[parentA] = parentB;
            }
        }

        // count element for each union-find group -> key: root of each union group, value: map
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int num = source[i];
            int root = find(uf, i);

            map.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> freqMap = map.get(root);
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // greedy fit the target, if not, diff++
        int res = 0;
        for (int i = 0; i < N; i++) {
            int num = target[i];
            int root = find(uf, i);

            Map<Integer, Integer> freqMap = map.get(root);

            if (freqMap.getOrDefault(num, 0) == 0) {
                res++;
            } else {
                freqMap.put(num, freqMap.get(num) - 1);
            }
        }

        return res;
    }

    private int find(int[] uf, int node) {
        if (uf[node] != node) {
            uf[node] = find(uf, uf[node]);
        }
        return uf[node];
    }
}
