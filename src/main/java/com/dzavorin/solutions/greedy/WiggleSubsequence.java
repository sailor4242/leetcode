package com.dzavorin.solutions.greedy;

public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) return 0;

        int res = 1;
        int prev = nums[0];
        int dir = 0;
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];

            if (n > prev) {
                if (dir <= 0) {
                    res++;
                }
                dir = 1;
            } else if (n < prev) {
                if (dir >= 0) {
                    res++;
                }
                dir = -1;
            }

            prev = n;
        }

        return res;
    }

    /////////

    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2) return nums.length;

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }

        return 1 + Math.max(down[nums.length - 1], up[nums.length - 1]);
    }

}
