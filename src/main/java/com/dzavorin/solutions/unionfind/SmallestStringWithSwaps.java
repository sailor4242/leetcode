package com.dzavorin.solutions.unionfind;

import java.util.*;

public class SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

        //uf
        int[] parent = new int[s.length()];
        for (int i = 0 ; i < s.length(); i++) {
            parent[i] = i;
        }

        for (List<Integer> pair : pairs) {
            parent[find(pair.get(0), parent)] = find(pair.get(1), parent);
        }

        //sorting and swaping
        char[] arr = s.toCharArray();

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0 ; i < s.length(); i++) {
            int rootId = find(i, parent);

            map.putIfAbsent(rootId, new ArrayList<>());
            map.get(rootId).add(i);
        }

        for (Integer k : map.keySet()) {
            List<Integer> idsList = map.get(k);
            if (idsList.size() == 1) continue;

            List<Character> chars = new ArrayList<>();
            for (int id : idsList) {
                chars.add(s.charAt(id));
            }
            Collections.sort(chars);
            int i = 0;
            for (Character ch : chars) {
                arr[idsList.get(i)] = ch;
                i++;
            }
        }

        return new String(arr);
    }


    private int find(int p, int[] parent) {
        if (p != parent[p]) {
            parent[p] = find(parent[p], parent);
        }
        return parent[p];
    }

}
