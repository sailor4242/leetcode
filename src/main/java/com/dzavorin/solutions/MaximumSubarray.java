package com.dzavorin.solutions;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {

        int s = 0;
        int r = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            s += nums[i];
            r = Math.max(r, s);
            s = Math.max(s, 0);
        }
        return r;
    }
}
