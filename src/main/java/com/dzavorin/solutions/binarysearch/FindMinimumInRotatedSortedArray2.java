package com.dzavorin.solutions.binarysearch;

public class FindMinimumInRotatedSortedArray2 {

    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] == nums[hi]) {
                hi--;
            } else {
                hi = mid;
            }
        }
        return Math.min(nums[0], nums[lo]);
    }

    public static void main(String[] args) {
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{3, 3, 1, 3}));
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{2, 2, 0, 1}));
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{1, 3, 3}));
        System.out.println(new FindMinimumInRotatedSortedArray2().findMin(new int[]{10,1,10,10,10}));
    }
}
