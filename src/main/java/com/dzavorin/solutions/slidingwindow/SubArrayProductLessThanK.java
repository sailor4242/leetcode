package com.dzavorin.solutions.slidingwindow;

public class SubArrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) return 0;

        int prod = 1;
        int res = 0;
        int lo = 0;

        for (int hi = 0; hi < nums.length; hi++) {
            prod *= nums[hi];
            while (prod >= k) {
                prod /= nums[lo++];
            }
            res += hi - lo + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SubArrayProductLessThanK()
                .numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100)); // 8

        System.out.println(new SubArrayProductLessThanK()
                .numSubarrayProductLessThanK(new int[]{10, 5, 1000, 6, 4}, 100)); // 6

        System.out.println(new SubArrayProductLessThanK()
                .numSubarrayProductLessThanK(new int[]{1, 1, 1}, 2)); // 6

        System.out.println(new SubArrayProductLessThanK()
                .numSubarrayProductLessThanK(new int[]{1, 2, 3}, 0)); // 0
    }
}
