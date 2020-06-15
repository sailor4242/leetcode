package com.dzavorin.solutions.dynamic.partition;

public class PartitionSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int n : nums) {
            sum += n;
        }

        if (sum % 2 != 0) {
            return false;
        }

        boolean[] dp = new boolean[sum / 2 + 1];
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = sum / 2; j >= nums[i]; j--) {
                dp[j] = dp[j] | dp[j - nums[i]];
            }
        }
        return dp[sum / 2];
    }

    public static void main(String[] args) {
        System.out.println(new PartitionSubsetSum().canPartition(new int[] {1,5,11,5}));
    }

}
