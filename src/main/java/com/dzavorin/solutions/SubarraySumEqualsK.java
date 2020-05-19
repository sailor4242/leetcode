package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                count++;
            }
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.compute(sum, (key, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{1,2,3}, 3));
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{28,54,7,-70,22,65,-6}, 100));
        System.out.println(new SubarraySumEqualsK().subarraySum(new int[]{0,0,0,0,0,0,0,0,0,0}, 0));
    }
}
