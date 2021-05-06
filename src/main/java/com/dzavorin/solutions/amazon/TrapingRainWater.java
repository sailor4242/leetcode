package com.dzavorin.solutions.amazon;

public class TrapingRainWater {

    public int trap(int[] height) {

        int lo = 0;
        int hi = height.length - 1;
        int loMax = 0;
        int hiMax = 0;
        int res = 0;

        while (lo < hi) {
            if (height[lo] < height[hi]) {
                if (height[lo] < loMax) {
                    res += loMax - height[lo];
                } else {
                    loMax = height[lo];
                }
                lo++;
            } else {
                if (height[hi] < hiMax) {
                    res += hiMax - height[hi];
                } else {
                    hiMax = height[hi];
                }
                hi--;
            }
        }
        return res;
    }

}
