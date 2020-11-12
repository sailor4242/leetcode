package com.dzavorin.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class RepeatedDNASequence {

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        for (int i = 0, j = 10; j <= s.length(); i++, j++) {

            String sub = s.substring(i, j);
            map.compute(sub, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });

        }
        return map.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // rolling hash example
    public List<String> findRepeatedDnaSequences2(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }

        Set<String> result = new HashSet<>();

        int windowSize = 10;
        int R = 101;
        Map<Long, List<Integer>> map = new HashMap<>();
        long patHash = 0;
        for (int j = 0; j < windowSize; j++) {
            patHash = (R * patHash + s.charAt(j));
        }
        map.computeIfAbsent(patHash, k -> new ArrayList<>()).add(0);

        // pre-compute R^(m-1) % q for use in removing leading digit
        long RM = 1;
        for (int i = 1; i <= windowSize - 1; i++) {
            RM = (R * RM);
        }

        for (int i = windowSize; i < s.length(); i++) {
            // Remove leading digit, add trailing digit, check for match.
            patHash = (patHash - RM * s.charAt(i - windowSize));
            patHash = (patHash * R + s.charAt(i));
            if (map.containsKey(patHash)) {
                for (int index : map.get(patHash)) {
                    String sub = s.substring(i - windowSize + 1, i + 1);
                    if (s.substring(index, index + windowSize).equals(sub)) {
                        result.add(sub);
                    }
                }
            }
            map.putIfAbsent(patHash, new ArrayList<>());
            map.get(patHash).add(i - windowSize + 1);
        }

        return new ArrayList<>(result);
    }

}
