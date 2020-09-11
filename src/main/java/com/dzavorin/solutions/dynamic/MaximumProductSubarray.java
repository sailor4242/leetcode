package com.dzavorin.solutions.dynamic;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {

        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];

        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = dpMax[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                dpMin[i] = Math.min(nums[i] * dpMax[i - 1], nums[i]);
                dpMax[i] = Math.max(nums[i] * dpMin[i - 1], nums[i]);
            } else {
                dpMin[i] = Math.min(nums[i] * dpMin[i - 1], nums[i]);
                dpMax[i] = Math.max(nums[i] * dpMax[i - 1], nums[i]);
            }

            res = Math.max(dpMax[i], res);
            res = Math.max(dpMin[i], res);
        }

        return res;
    }
}
