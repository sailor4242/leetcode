package com.dzavorin.solutions.math;

public class MinimumElementsToAddToFormGivenSum {

    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int n : nums) {
            sum += n;
        }

        int added = 0;
        if (sum != goal) {
            long diff = Math.abs(sum - goal);
            added += diff / limit;
            if (diff % limit != 0) {
                added++;
            }
        }

        return added;
    }


}
