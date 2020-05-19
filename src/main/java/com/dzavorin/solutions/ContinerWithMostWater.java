package com.dzavorin.solutions;

public class ContinerWithMostWater {

    public int maxArea(int[] nums) {

        int lo = 0;
        int hi = nums.length - 1;
        int s = 0;

        while (hi > lo) {

            s = Math.max(s, Math.min(nums[lo], nums[hi]) * (hi - lo));

            if (nums[hi] < nums[lo]) {
                hi--;
            } else {
                lo++;
            }

        }

        return s;

    }

    public static void main(String[] args) {
        System.out.println(new ContinerWithMostWater().maxArea(new int[]{2,3,4,5,18,17,6}));
    }

}
