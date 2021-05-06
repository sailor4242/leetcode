package com.dzavorin.solutions;

public class ContinerWithMostWater {

    public int maxArea(int[] height) {
        int lo = 0;
        int hi = height.length - 1;

        int max = 0;

        while (lo < hi) {
            max = Math.max(max, (hi - lo) * Math.min(height[hi], height[lo]));

            if (height[hi] > height[lo]) {
                lo++;
            } else {
                hi--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new ContinerWithMostWater().maxArea(new int[]{2,3,4,5,18,17,6}));
        System.out.println(Math.log(50000) / Math.log(2));
    }

}
