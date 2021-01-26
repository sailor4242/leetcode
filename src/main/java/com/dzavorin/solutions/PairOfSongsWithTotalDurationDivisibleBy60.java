package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairOfSongsWithTotalDurationDivisibleBy60 {

    public int numPairsDivisibleBy60(int[] time) {

        List<Integer> periods = new ArrayList<>();

        int period = 60;
        int c = 1;
        while (period * c < 1000) {
            periods.add(period * c++);
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int t : time) {
            for (int p : periods) {
                if (map.containsKey(p - t)) {
                    res += map.get(p - t);
                }
            }
            map.put(t, map.getOrDefault(t, 0) + 1);
        }

        return res;
    }

    ///

    public int numPairsDivisibleBy602(int[] time) {
        int[] remainders = new int[60];
        int count = 0;
        for (int t : time) {
            if (t % 60 == 0) {
                count += remainders[0];
            } else {
                count += remainders[60 - t % 60];
            }
            remainders[t % 60]++;
        }
        return count;
    }

}
