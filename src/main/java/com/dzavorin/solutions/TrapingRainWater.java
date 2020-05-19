package com.dzavorin.solutions;

public class TrapingRainWater {

    public int trap(int[] nums) {

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int k = 0;
            int maxI = 0;
            int max = 0;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > max) {
                    maxI = j;
                    max = nums[j];
                }
                if (nums[j] >= nums[i]) {
                    k = j;
                    break;
                }
            }

            if (k == 0) {
                if (max != 0) {
                    for (int l = i + 1; l < maxI; l++) {
                        sum += max - nums[l];
                    }

                    i = maxI - 1;
                }
                continue;
            } else {

                for (int l = i + 1; l < k; l++) {
                    sum += nums[i] - nums[l];
                }

                i = k - 1;
            }

        }
        return sum;

    }

    public static void main(String[] args) {
        System.out.println(new TrapingRainWater().trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(new TrapingRainWater().trap(new int[]{4,2,3}));
    }
}
