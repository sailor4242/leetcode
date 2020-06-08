package com.dzavorin.solutions.dynamic;

import java.util.*;

public class LargestDivisibleSubset {

    public List<Integer> largestDivisibleSubset(int[] nums) {

        int n = nums.length;
        if (n == 0) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        int[] dp = new int[n];
        int[] prev = new int[n];

        int max = dp[0];
        int idxMax = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {

                    dp[i] = dp[j] + 1;
                    prev[i] = j;

                    if (max < dp[i]) {
                        max = dp[i];
                        idxMax = i;
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        res.add(nums[idxMax]);

        while (prev[idxMax] != -1) {
            idxMax = prev[idxMax];
            res.add(nums[idxMax]);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[]{4, 8, 10, 16, 240}));
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[]{2, 3, 4, 9, 8}));
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[]{1, 2, 3}));
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[]{1, 2, 4, 8}));
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[]{1}));
        System.out.println(new LargestDivisibleSubset().largestDivisibleSubset(new int[]{2, 3}));
    }

}
