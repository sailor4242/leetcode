package com.dzavorin.solutions.arrays;

import java.util.Arrays;

public class RotateArray {

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - k);
        reverse(nums, 0, nums.length);
        reverse(nums, 0, k);
    }

    public void reverse(int[] nums, int lo, int hi) {
        int m = hi / 2;
        for (int i = lo; i < m; i++) {
            int tmp = nums[i];
            nums[i] = nums[hi - 1 - i];
            nums[hi - 1 - i] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2};
        new RotateArray().rotate(nums, 3);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = new int[]{-1};
        new RotateArray().rotate(nums2, 2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = new int[]{1,2,3};
        new RotateArray().rotate(nums3, 4);
        System.out.println(Arrays.toString(nums3));
    }
}
