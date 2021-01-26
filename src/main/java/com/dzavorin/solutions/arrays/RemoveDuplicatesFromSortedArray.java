package com.dzavorin.solutions.arrays;

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int diff = 0;
        if (nums.length == 0) {
            return 0;
        }
        int prev = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            nums[i - diff] = nums[i];
            if (nums[i] == prev) {
                count++;
                if (count > 2) {
                    diff++;
                }
            } else {
                prev = nums[i];
                count = 1;
            }
        }
        return nums.length - diff;
    }

    /////

    public int removeDuplicates2(int[] nums) {

        int diff = 0;
        Integer dupl = null;
        for (int i = 0; i < nums.length - 2; i++) {
            int prevPrev = nums[i];
            int prev = nums[i + 1];
            int cur = nums[i + 2];

            if ((prevPrev == prev && prev == cur) || (dupl != null && cur == dupl)) {
                diff++;
                dupl = cur;
                nums[i + 2] = Integer.MIN_VALUE;
            } else {
                dupl = null;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == Integer.MIN_VALUE) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] != Integer.MIN_VALUE) {
                        nums[i] = nums[j];
                        nums[j] = Integer.MIN_VALUE;
                        break;
                    }
                }
            }
        }

        return nums.length - diff;
    }

}
