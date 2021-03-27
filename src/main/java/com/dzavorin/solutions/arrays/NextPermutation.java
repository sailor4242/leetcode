package com.dzavorin.solutions.arrays;

import java.util.Arrays;

public class NextPermutation {

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

    private void reverse(int[] nums, int k) {
        int j = nums.length - 1;
        for (int i = k; i < (k + nums.length) / 2; i++) {
            swap(nums, i, j--);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
//        int[] t1 = new int[]{1, 2, 3};
//        new NextPermutation().nextPermutation(t1);
//        System.out.println(Arrays.toString(t1)); //1, 3, 2
//
//        int[] t5 = new int[]{1, 3, 2};
//        new NextPermutation().nextPermutation(t5);
//        System.out.println(Arrays.toString(t5)); //2,1,3
//
//        int[] t2 = new int[]{3, 2, 1};
//        new NextPermutation().nextPermutation(t2);
//        System.out.println(Arrays.toString(t2)); //1,2,3
//
//        int[] t3 = new int[]{1, 1, 5};
//        new NextPermutation().nextPermutation(t3);
//        System.out.println(Arrays.toString(t3)); //1,5,1

        int[] t4 = new int[]{4, 2, 0, 2, 3, 2, 0};
        new NextPermutation().nextPermutation(t4);
        System.out.println(Arrays.toString(t4)); //4,2,0,3,0,2,2
    }
}
