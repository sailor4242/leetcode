package com.dzavorin.solutions.slidingwindow;

import java.util.TreeSet;

public class ContainsDuplicate3 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length > 9999) return false;
        int size = 1;
        while (size <= k) {
            for (int i = 0; i < nums.length - size; i++) {
                if (Math.abs((long) nums[i] - (long) nums[i + size]) <= t) {
                    return true;
                }
            }
            size++;
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {

        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {

            Integer l = set.floor(nums[i]);
            Integer r = set.ceiling(nums[i]);

            if ((l != null && (long) nums[i] - l <= t)
                    || (r != null && (long) r - nums[i] <= t)) {
                return true;
            }

            set.add(nums[i]);
            if (i >= k) {
                set.remove (nums[i - k]);
            }

        }

        return false;
    }

}
