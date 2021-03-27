package com.dzavorin.solutions.bits;

import java.util.HashMap;
import java.util.Map;

public class MakeTheXOROfAllSegmentsEqualToZero {

    public int minChanges(int[] nums, int k) {

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0 ; i < nums.length; i++) {
            int key = i % k;
            map.putIfAbsent(key, new HashMap<>());
            Map<Integer, Integer> counter = map.get(key);
            counter.put(nums[i], counter.getOrDefault(nums[i], 0) + 1);
        }

        int res = 0;
        for (Map<Integer, Integer> countsMap : map.values()) {
            int sum = 0;
            int max = 0;
            for (int n : countsMap.values()) {
                sum += n;
                max = Math.max(n, max);
            }

            res += sum - max;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(2^1^3);
    }

}
