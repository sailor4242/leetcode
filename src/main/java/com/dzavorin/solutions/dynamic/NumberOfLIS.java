package com.dzavorin.solutions.dynamic;

import java.util.Arrays;
import java.util.Comparator;

public class NumberOfLIS {

    public int findNumberOfLIS(int[] nums) {

        int[][] dp = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j][0] + 1 == dp[i][0]) {
                        dp[i][1] += dp[j][1];
                    } else if (dp[j][0] + 1 > dp[i][0]) {
                        dp[i][0] = dp[j][0] + 1;
                        dp[i][1] = dp[j][1];
                    }
                }
            }
        }

        Arrays.sort(dp, Comparator.comparing(a -> -a[0]));
        int max = dp[0][0];
        int n = dp[0][1];
        for (int i = 1; i < dp.length; i++) {
            if (dp[i][0] == max) {
                n += dp[i][1];
            } else {
                break;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfLIS().findNumberOfLIS(new int[]{2,2,2,2,2}));
        System.out.println(new NumberOfLIS().findNumberOfLIS(new int[]{1,3,5,4,7}));
        System.out.println(new NumberOfLIS().findNumberOfLIS(new int[]{1,2,4,3,5,4,7,2}));
    }

}
