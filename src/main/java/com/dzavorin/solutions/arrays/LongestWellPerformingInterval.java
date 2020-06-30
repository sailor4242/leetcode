package com.dzavorin.solutions.arrays;

import java.util.HashMap;
import java.util.Map;

public class LongestWellPerformingInterval {

    public int longestWPI(int[] hours) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, -1);
        int output = 0;

        for (int i = 0; i < hours.length; i++) {
            sum = (hours[i] > 8) ? sum + 1 : sum - 1;
            if (sum > 0) {
                output = Math.max(output, i - map.get(0));
            } else {
                if (map.containsKey(sum - 1)) {
                    output = Math.max(output, i - map.get(sum - 1));
                }
                if (!map.containsKey(sum)) {
                    map.put(sum, i);
                }
            }
        }
        return output;
    }
}
