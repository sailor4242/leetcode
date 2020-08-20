package com.dzavorin.solutions.slidingwindow;

public class MaximumAverageSubarray1 {

    public double findMaxAverage(int[] nums, int k) {

        int c = 0;
        int sum = 0;
        double res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {

            if (c != k) {
                c++;
                sum += nums[i];
                if (c == k) {
                    res = Math.max(res, (double) sum / k);
                }
            } else {
                sum -= nums[i - k];
                sum += nums[i];
                res = Math.max(res, (double) sum / k);
            }

        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(new MaximumAverageSubarray1().findMaxAverage(new int[]{-1}, 1));
    }
}
