package com.dzavorin.solutions.greedy;

import java.util.HashSet;
import java.util.Set;

public class MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget {

    //O(N)
    public int maxNonOverlapping(int[] nums, int target) {

        int count = 0;
        Set<Integer> set = new HashSet<>();

        int sum = 0;
        for (int num : nums) {
            sum += num;

            if (sum == target || set.contains(sum - target)) {
                count++;
                sum = 0;
                set.clear();
            } else {
                set.add(sum);
            }
        }

        return count;
    }

    //O(N^2) greedy
    public int maxNonOverlapping2(int[] nums, int target) {

        int[] dp = new int[nums.length];
        int k = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i];
            if (nums[i] == target) {
                count++;
                k = i;
                continue;
            }
            for (int j = k + 1; j <= i - 1; j++) {
                dp[j] += nums[i];
                if (dp[j] == target) {
                    count++;
                    k = i;
                    break;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget()
                .maxNonOverlapping2(new int[]{-2, 6, 6, 3, 5, 4, 1, 2, 8}, 10)); // 3

        System.out.println(new MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget()
                .maxNonOverlapping2(new int[]{-5, 5, -4, 5, 4}, 5)); // 2

        System.out.println(new MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget()
                .maxNonOverlapping2(new int[]{1, 1, 1, 1, 1}, 2)); // 2

        System.out.println(new MaximumNumberOfNonOverlappingSubarraysWithSumEqualsTarget()
                .maxNonOverlapping2(new int[]{2, 2, 3, 3, -3, -1, 2, -3}, 4)); // 2
    }

}
