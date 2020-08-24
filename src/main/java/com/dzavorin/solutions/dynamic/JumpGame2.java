package com.dzavorin.solutions.dynamic;

import java.util.Arrays;

public class JumpGame2 {

    public int jump(int[] nums) {

        int[] memo = new int[nums.length];
        Arrays.fill(memo, nums.length - 1);
        return jump(nums, 0, memo);
    }

    public int jump(int[] nums, int i, int[] memo) {

        if (i >= nums.length - 1) {
            return 0;
        }

        if (memo[i] != nums.length - 1) {
            return memo[i];
        }

        for (int j = nums[i]; j > 0; j--) {
            memo[i] = Math.min(memo[i], 1 + jump(nums, i + j, memo));
            if (memo[i] <= 2) {
                return memo[i];
            }
        }

        return memo[i];

    }

    public int jump2(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        int maxPos = nums[0];
        int maxSteps = nums[0];
        int jumps = 1;
        for (int i = 0; i < nums.length; i++) {
            if (maxSteps < i) {
                jumps++;
                maxSteps = maxPos;
            }
            maxPos = Math.max(maxPos, nums[i] + i);
        }
        return jumps;
    }

    public int jump3(int[] nums) {

        int[] dp = new int[nums.length];

        Arrays.fill(dp, nums.length);
        dp[nums.length - 1] = 0;

        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < nums.length) {
                    dp[i] = Math.min(dp[i], 1 + dp[i + j]);
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame2().jump(new int[]{2, 3, 1, 1, 4})); // 2
        System.out.println(new JumpGame2().jump(new int[]{9, 8, 2, 2, 0, 2, 2, 0, 4, 1, 5, 7, 9, 6, 6, 0, 6, 5, 0, 5})); // 3
        System.out.println(new JumpGame2().jump(new int[]{1, 2})); // 1
    }

}
