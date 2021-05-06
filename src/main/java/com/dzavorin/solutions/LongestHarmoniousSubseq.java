package com.dzavorin.solutions;

import java.util.Arrays;
import java.util.HashMap;

public class LongestHarmoniousSubseq {

    // n with n memory
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.containsKey(num + 1))
                res = Math.max(res, map.get(num) + map.get(num + 1));
            if (map.containsKey(num - 1))
                res = Math.max(res, map.get(num) + map.get(num - 1));
        }
        return res;
    }


    // nlogn
    public int findLHS2(int[] nums) {
        if (nums.length <= 1) return 0;

        int res = 0;
        Arrays.sort(nums);
        int start = 0;
        int nextstart = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[start] == 1) {
                if (nums[nextstart] < nums[i]) {
                    nextstart = i;
                }
                res = Math.max(res, i - start + 1);
            } else if (nums[i] - nums[start] > 1) {
                start = start == nextstart ? i : nextstart;
                i--;
            }
        }
        return res;
    }
}
