package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i ++) {
            Integer n = map.get(target - nums[i]);
            if (n != null) {
                return new int[]{n, i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }

}
