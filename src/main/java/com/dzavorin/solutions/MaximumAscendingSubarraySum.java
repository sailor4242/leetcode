package com.dzavorin.solutions;

public class MaximumAscendingSubarraySum {

    public int maxAscendingSum(int[] nums) {
        if (nums.length == 0) return 0;

        int max = nums[0];
        int curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                curSum += nums[i];

            } else {
                curSum = nums[i];
            }

            max = Math.max(curSum, max);

        }

        return max;
    }

}
