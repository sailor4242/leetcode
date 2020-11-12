package com.dzavorin.solutions.binarysearch;

public class FindTheSmallestDivisorGivenAThreshold {

    public int smallestDivisor(int[] nums, int threshold) {
        int max = Integer.MIN_VALUE;
        for (int n : nums) {
            max = Math.max(n, max);
        }

        int lo = 1;
        int hi = max;
        while (lo <= hi) {
            int m = lo + (hi - lo) / 2;
            if (divideAndSum(nums, m) > threshold) {
                lo = m + 1;
            } else {
                hi = m - 1;
            }

        }

        return lo;

    }

    public int divideAndSum(int[] nums, int divisor) {
        int sum = 0;
        for (int n : nums) {
            sum += n % divisor == 0 ? n / divisor : n / divisor + 1;
        }

        return sum;
    }

}
