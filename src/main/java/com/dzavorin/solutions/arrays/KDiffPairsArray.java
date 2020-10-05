package com.dzavorin.solutions.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class KDiffPairsArray {

    public int findPairs(int[] nums, int k) {

        Map<Integer, Set<Integer>> pairsMap = new HashMap<>();
        Map<Integer, Integer> selfIdMap = new HashMap<>();
        int res = 0;


        for (int i = 0; i < nums.length; i++) {
            pairsMap.putIfAbsent(nums[i], new HashSet<>());
            selfIdMap.putIfAbsent(nums[i], i);


            int diffOne = nums[i] - k;
            if (pairsMap.containsKey(diffOne)
                    && !pairsMap.get(diffOne).contains(nums[i])
                    && selfIdMap.get(diffOne) != i

            ) {
                pairsMap.get(diffOne).add(nums[i]);
                pairsMap.get(nums[i]).add(diffOne);
                res++;
            }

            int diffTwo = nums[i] + k;
            if (pairsMap.containsKey(diffTwo)
                    && !pairsMap.get(diffTwo).contains(nums[i])
                    && selfIdMap.get(diffTwo) != i

            ) {
                pairsMap.get(diffTwo).add(nums[i]);
                pairsMap.get(nums[i]).add(diffTwo);
                res++;
            }
        }

        return res;
    }

    public int findPairs2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int result = 0;
        for (int n : map.keySet())
            if (k > 0 && map.containsKey(n + k) || k == 0 && map.get(n) > 1)
                result++;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new KDiffPairsArray().findPairs2(new int[]{1, 3, 1, 5, 4}, 0));
        System.out.println(new KDiffPairsArray().findPairs2(new int[]{4, 2}, 2));
    }

}
