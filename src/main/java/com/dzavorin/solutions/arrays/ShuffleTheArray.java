package com.dzavorin.solutions.arrays;

public class ShuffleTheArray {

    public int[] shuffle(int[] nums, int n) {

        int[] res = new int[n * 2];
        int j = 0;

        for (int i = 0; i < n; i++) {
            res[j++] = nums[i];
            res[j++] = nums[n + i];
        }

        return res;

    }

}
