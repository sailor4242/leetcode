package com.dzavorin.solutions.arrays;

import java.util.*;

public class PartitionLabels {

    // O(n^2)
    public List<Integer> partitionLabels(String S) {
        char[] arr = S.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : arr) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        Set<Character> visited = new HashSet<>();
        int count = 0;
        for (char ch : arr) {
            visited.add(ch);
            int n = map.get(ch) - 1;
            map.put(ch, n);
            count++;

            if (n == 0 && visited.stream().allMatch(v -> map.get(v) == 0)) {
                res.add(count);
                visited = new HashSet<>();
                count = 0;
            }

        }

        return res;
    }

    // O(n)
    public List<Integer> partitionLabels2(String S) {
        int[] lastIdxs = new int[26];
        for (int i = 0; i < S.length(); i++)
            lastIdxs[S.charAt(i) - 'a'] = i;

        List<Integer> res = new ArrayList<>();
        int j = 0;
        int subStrStart = 0;

        for (int i = 0; i < S.length(); i++) {
            j = Math.max(j, lastIdxs[S.charAt(i) - 'a']);
            if (i == j) {
                res.add(i - subStrStart + 1);
                subStrStart = i + 1;
            }
        }

        return res;
    }

}
