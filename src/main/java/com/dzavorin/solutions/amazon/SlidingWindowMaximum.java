package com.dzavorin.solutions.amazon;

import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> list = new LinkedList<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            while (!list.isEmpty() && nums[list.getLast()] < nums[i]) {
                list.removeLast();
            }
            list.add(i);
            if (i - list.getFirst() >= k) {
                list.removeFirst();
            }
            if (i >= k - 1) {
                res[i - k + 1] = nums[list.getFirst()];
            }
        }
        return res;
    }

}
