package com.dzavorin.solutions.sorting;

public class SortColors {

    public void sortColors2(int[] nums) {
        int[] cc = new int[3];
        for (int n : nums) {
            cc[n]++;
        }

        int k = 0;
        for (int i = 0; i < cc.length; i++) {
            int c = cc[i];
            for (int j = 0; j < c; j++) {
                nums[k++] = i;
            }
        }
    }


    public void sortColors(int[] nums) {
        int n = nums.length;
        int low = 0;
        int mid = 0;
        int high = n - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
