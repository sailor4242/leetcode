package com.dzavorin.solutions.greedy;

import java.util.HashMap;
import java.util.Map;

public class SplitArrayIntoConsecutiveSubsequences {

    public boolean isPossible(int[] nums) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (!freq.containsKey(num)) {
                continue;
            }

            int minOccur = 1;
            int tmp = num;

            while (freq.containsKey(tmp) && freq.get(tmp) >= minOccur) {
                minOccur = Math.max(minOccur, freq.get(tmp));
                freq.put(tmp, freq.get(tmp) - 1);

                if (freq.get(tmp) == 0) {
                    freq.remove(tmp);
                }
                tmp++;
            }

            if (tmp - num < 3) {
                return false;
            }
        }

        return freq.isEmpty();
    }

    public static void main(String[] args) {
//        System.out.println(new SplitArrayIntoConsecutiveSubsequences().isPossible(new int[]{1,2,3, 3, 4,5,}));
        System.out.println(new SplitArrayIntoConsecutiveSubsequences().isPossible(new int[]{1,2,3,4}));
        System.out.println(new SplitArrayIntoConsecutiveSubsequences().isPossible(new int[]{1,2,3, 4,5, 6}));
    }
}
