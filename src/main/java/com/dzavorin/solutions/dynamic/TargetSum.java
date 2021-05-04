package com.dzavorin.solutions.dynamic;

public class TargetSum {

    //// O(n*l) time and space, l - refers to a range of sum, was constrained like -1000 <-> +1000

    public int findTargetSumWays(int[] nums, int S) {
        return dfs(nums, S, 0, 0, new Integer[nums.length][2002]);
    }

    private int dfs(int[] nums, int sum, int i, int curSum, Integer[][] memo) {
        if (curSum == sum && i == nums.length) {
            return 1;
        }

        if (i >= nums.length) return 0;

        int j = curSum + 1000;

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int res = 0;
        int n = nums[i];

        res += dfs(nums, sum, i + 1, curSum + n, memo);
        res += dfs(nums, sum, i + 1, curSum - n, memo);

        return memo[i][j] = res;
    }

    ///// O(n*l) time, O(n) space

    public int findTargetSumWays2(int[] nums, int S) {
        int[] dp = new int[2001];
        dp[nums[0] + 1000] = 1;
        dp[-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            int[] next = new int[2001];
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[sum + 1000] > 0) {
                    next[sum + nums[i] + 1000] += dp[sum + 1000];
                    next[sum - nums[i] + 1000] += dp[sum + 1000];
                }
            }
            dp = next;
        }
        return S > 1000 ? 0 : dp[S + 1000];
    }


}
