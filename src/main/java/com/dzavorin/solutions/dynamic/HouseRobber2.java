package com.dzavorin.solutions.dynamic;

public class HouseRobber2 {

    public int rob(int[] nums) {

        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for (int i = 2; i < nums.length - 1; i++) {
            dp[i] = Math.max(
                    dp[i - 2] + nums[i],
                    i - 3 >= 0 ? nums[i] + dp[i - 3] : 0
            );
        }

        int[] dp2 = new int[nums.length];

        dp2[1] = nums[1];

        for (int i = 2; i < nums.length; i++) {
            dp2[i] = Math.max(
                    dp2[i - 2] + nums[i],
                    i - 3 >= 0 ? nums[i] + dp2[i - 3] : 0
            );
        }


        return Math.max(
                Math.max(dp[nums.length - 2], dp[nums.length - 3]),
                Math.max(dp2[nums.length - 1], dp2[nums.length - 2])
        );
    }

}
