package com.dzavorin.solutions.monotonicqueue;

import java.util.Stack;
import java.util.TreeMap;

public class OneThreeTwoPattern {

    public boolean find132pattern(int[] nums) {
        Stack<Integer> stk = new Stack<>();
        int numsJ = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < numsJ)
                return true;

            while (!stk.isEmpty() && nums[i] > stk.peek())
                numsJ = stk.pop();

            stk.push(nums[i]);
        }
        return false;
    }

    public boolean find132pattern2(int[] nums) {

        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            Integer lowerKey = map.lowerKey(num);
            if (lowerKey != null) {
                if (num < map.get(lowerKey)) {
                    return true;
                }
                map.put(lowerKey, num);
                continue;
            }
            if (num < min) {
                min = num;
                max = num;
            } else if (num > max) {
                max = num;
                map.put(min, max);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new OneThreeTwoPattern().find132pattern(new int[]{3, 1, 4, 2}));
    }
}
