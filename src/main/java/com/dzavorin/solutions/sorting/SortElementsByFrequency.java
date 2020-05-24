package com.dzavorin.solutions.sorting;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortElementsByFrequency {

    // bucket sort

    public String frequencySort(String s) {
        if (s.length() < 3)
            return s;
        int max = 0;
        int[] map = new int[256];
        for (char ch : s.toCharArray()) {
            map[ch]++;
            max = Math.max(max, map[ch]);
        }
        String[] buckets = new String[max + 1]; // create max buckets
        for (int i = 0; i < 256; i++) { // join chars in the same bucket
            String str = buckets[map[i]];
            if (map[i] > 0)
                buckets[map[i]] = (str == null) ? "" + (char) i : (str + (char) i);
        }
        StringBuilder strb = new StringBuilder();
        for (int i = max; i >= 0; i--) { // create string for each bucket.
            if (buckets[i] != null)
                for (char ch : buckets[i].toCharArray())
                    for (int j = 0; j < i; j++)
                        strb.append(ch);
        }
        return strb.toString();
    }

    public String frequencySort2(String s) {

        Map<Character, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        map.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .forEach(e -> {
                    for (int i = 0; i < e.getValue(); i++) {
                        sb.append(e.getKey());
                    }
                });

        return sb.toString();
    }
}
