package com.dzavorin.solutions;

import java.util.Stack;

public class ShortestUnsortedContiniousSubarray {

    ////

    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        int lo = nums.length, hi = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                lo = Math.min(lo, stack.pop());
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                hi = Math.max(hi, stack.pop());
            }
            stack.push(i);
        }

        return hi - lo < 0 ? 0 : hi - lo + 1;
    }


    /// same logic but without stack


    public int findUnsortedSubarray2(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        boolean flag = false;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }
            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }

        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }
            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }

        int lo = 0;
        while (lo < nums.length) {
            if (min < nums[lo]) {
                break;
            }
            lo++;
        }

        int hi = nums.length - 1;
        while (hi >= 0) {
            if (max > nums[hi]) {
                break;
            }
            hi--;
        }

        return hi - lo < 0 ? 0 : hi - lo + 1;
    }

}
