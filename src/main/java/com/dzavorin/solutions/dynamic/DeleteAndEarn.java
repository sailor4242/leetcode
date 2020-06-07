package com.dzavorin.solutions.dynamic;

import java.util.*;

public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + num);
        }

        List<Integer> list = new ArrayList<>(freq.keySet());
        Collections.sort(list);

        int[] dp = new int[list.size()];
        dp[0] = freq.get(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            int cur = list.get(i);
            int curSum = freq.get(cur);

            if (cur == list.get(i - 1) + 1) {
                dp[i] = Math.max(dp[i - 1], curSum + (i - 2 >= 0 ? dp[i - 2] : 0));
            } else {
                dp[i] = curSum + dp[i - 1];
            }
        }

        return dp[list.size() - 1];
    }

    public int deleteAndEarn3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        if (max == 1) {
            return nums.length;
        }
        int[] dp = new int[max + 1];

        for (int num : nums) {
            dp[num] += num;
        }

        dp[dp.length - 2] = Math.max(dp[dp.length - 2], dp[dp.length - 1]);

        for (int i = dp.length - 3; i >= 1; i--) {
            dp[i] = Math.max(dp[i + 1], dp[i] + dp[i + 2]);
        }
        return Math.max(dp[1], dp[2]);
    }

    public static void main(String[] args) {
        System.out.println(new DeleteAndEarn().deleteAndEarn(new int[]{1, 1, 1, 2, 4, 5, 5, 5, 6}));
        System.out.println(new DeleteAndEarn().deleteAndEarn(new int[]{3, 4, 2}));
    }
}
