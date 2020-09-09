package com.dzavorin.solutions.arrays;

public class PermutationSequence {

    public String getPermutation(int n, int k) {

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }


        while (k != 1) {
            nextPermutation(arr);
            k--;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] > nums[i]) {
                    swap(nums, i , j);
                    reverse(nums, i + 1);
                    return;
                }
            }
        }

        reverse(nums, 0);
    }

    public void reverse(int[] nums, int i) {
        int j = nums.length - 1;
        int k = i;
        while (k < (i + nums.length) / 2) {
            swap(nums, k, j--);
            k++;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
