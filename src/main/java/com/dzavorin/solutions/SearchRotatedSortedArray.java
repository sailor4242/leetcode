package com.dzavorin.solutions;

public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        int j = 0;
        int s = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= s) {
                s = nums[i];
                j = i;
            }
        }

        return search(nums, target, j);
    }

    public int search(int[] nums, int target, int p) {
        int k = -1;
        if (nums.length == 0) {
            return k;
        }
        if (target >= nums[0] && target <= nums[p]) {
            k = bSearch(target, 0, p, nums);
        } else {
            k = bSearch(target, p + 1, nums.length - 1, nums);
        }
        return k;
    }

    public int bSearch(int target, int lo, int hi, int[] nums) {
        if (hi < lo) {
            return -1;
        }
        int i = (lo + hi) / 2;
        if (target == nums[i]) {
            return i;
        } else if (target > nums[i]) {
            return bSearch(target, i + 1, hi, nums);
        } else {
            return bSearch(target, lo, i - 1, nums);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SearchRotatedSortedArray().search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{}, 5));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{1}, 1));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{3,1}, 1));
    }
}
