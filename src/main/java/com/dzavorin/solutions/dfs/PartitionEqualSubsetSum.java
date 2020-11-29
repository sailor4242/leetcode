package com.dzavorin.solutions.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        if (totalSum % 2 != 0) return false;
        int subSetSum = totalSum / 2;
        int n = nums.length;

        Boolean[][] memo = new Boolean[n + 1][subSetSum + 1];

        return dfs(nums, n - 1, subSetSum, memo);
    }

    public boolean dfs(int[] nums, int n, int subSetSum, Boolean[][] memo) {
        if (subSetSum == 0)
            return true;
        if (n == 0 || subSetSum < 0)
            return false;

        if (memo[n][subSetSum] != null)
            return memo[n][subSetSum];
        boolean result = dfs(nums, n - 1, subSetSum - nums[n - 1], memo)
                        || dfs(nums, n - 1, subSetSum, memo);

        memo[n][subSetSum] = result;
        return result;
    }

    //////////////

    public boolean canPartition3(int[] nums) {
        if (nums.length == 0)
            return false;
        int totalSum = 0;
        for (int n : nums)
            totalSum += n;
        if (totalSum % 2 != 0) return false;
        int subsetSum = totalSum / 2;

        boolean[] dp = new boolean[subsetSum + 1];
        dp[0] = true;
        for (int n : nums) {
            for (int j = subsetSum; j >= n; j--)
                dp[j] = dp[j] | dp[j - n];
        }
        return dp[subsetSum];
    }

    ////////////

    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if (sum % 2 != 0) return false;
        Arrays.sort(nums);

        int half = sum / 2;
        return dfs(nums, sum / 2, 0, 0, new HashMap<String, Boolean>());
    }

    boolean dfs(int[] nums, int sum, int cur, int j, Map<String, Boolean> memo) {
        if (cur == sum) return true;

        String key = cur + "_" + j;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        boolean res = false;
        for (int i = j; i < nums.length; i++) {
            if (cur + nums[i] <= sum && dfs(nums, sum, cur + nums[i], i + 1, memo)) {
                res = true;
                break;
            }
        }

        memo.put(key, res);
        return res;
    }
}
