package com.dzavorin.solutions.amazon;

import java.util.HashMap;
import java.util.Map;

public class PairsOfSongsWithTotalDurationDivisibleBy60 {

    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int count = 0;
        for (int t : time) {
            int mod = t % 60;
            if (mod == 0) {
                count += map.get(0);
            } else if (map.containsKey(60 - mod)) {
                count += map.get(60 - mod);
            }

            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }

        return count;
    }
}
