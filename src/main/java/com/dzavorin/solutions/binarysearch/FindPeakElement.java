package com.dzavorin.solutions.binarysearch;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int m = lo + (hi - lo) / 2;
            if (nums[m] > nums[m + 1]) {
                hi = m;
            } else {
                lo = m + 1;
            }
        }
        return lo;
    }

    public static void main(String[] args) {
        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,2,2,3,4,99}));
        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,2,3,4,3,3,1}));
        System.out.println(new FindPeakElement().findPeakElement(new int[]{1,5, 2}));
    }
}
