package com.dzavorin.solutions.arrays;

public class NumberOfGoodPairs {

    public int numIdenticalPairs(int[] nums) {

        int[] freq = new int[101];
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            if (freq[nums[i]] != 0) {
                ans += freq[nums[i]];
            }
            freq[nums[i]] = freq[nums[i]] + 1;
        }
        return ans;
    }

}
