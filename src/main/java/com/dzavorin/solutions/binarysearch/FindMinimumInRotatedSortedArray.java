package com.dzavorin.solutions.binarysearch;

public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{3, 1, 2}));
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(new int[]{6, 9, 1, 2, 3, 4, 5}));
    }

}
