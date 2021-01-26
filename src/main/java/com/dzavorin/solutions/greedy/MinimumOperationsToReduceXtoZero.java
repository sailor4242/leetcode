package com.dzavorin.solutions.greedy;

import java.util.HashMap;
import java.util.Map;

public class MinimumOperationsToReduceXtoZero {

    public int minOperations(int[] nums, int x) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int t = sum - x;
        int prefixSum = 0;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            map.put(prefixSum, i);
            Integer j = map.get(prefixSum - t);
            if (j != null) {
                res = Math.min(res, nums.length - (i - j));
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumOperationsToReduceXtoZero().minOperations(new int[]{1, 1, 4, 2, 3}, 5)); // 2
        System.out.println(new MinimumOperationsToReduceXtoZero().minOperations(new int[]{3, 2, 20, 1, 1, 3}, 10)); // 5
    }

}
