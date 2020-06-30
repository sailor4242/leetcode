package com.dzavorin.solutions.arrays;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int max = 0;

        for (int n : set) {
            if (!set.contains(n - 1)) {
                int curN = n;
                int curMax = 1;

                while (set.contains(curN + 1)) {
                    curN++;
                    curMax++;
                }

                max = Math.max(max, curMax);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{1, 0, -1}));
        System.out.println(new LongestConsecutiveSequence().longestConsecutive(new int[]{1, 2, 0, 1}));
    }
}
