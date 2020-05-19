package com.dzavorin.solutions;

public class MaximumCircularSumSubArray {

    public int maxSubarraySumCircular(int[] A) {
        int total = 0, maxSum = -30000, curMax = 0, minSum = 30000, curMin = 0;
        for (int a : A) {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumCircularSumSubArray().maxSubarraySumCircular(new int[]{5,-3,5}));
        System.out.println(new MaximumCircularSumSubArray().maxSubarraySumCircular(new int[]{3,-1,2,-1}));
    }

    // 5 -3 5 5 -3 5
}
