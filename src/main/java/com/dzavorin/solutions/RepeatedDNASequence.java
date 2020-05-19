package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RepeatedDNASequence {

    public List<String> findRepeatedDnaSequences(String s) {
        Map<String, Integer> map = new HashMap<>();
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        for (int i = 0, j = 10; j <= s.length(); i++, j++) {

            String sub = s.substring(i, j);
            map.compute(sub, (k,v) -> {
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

}
