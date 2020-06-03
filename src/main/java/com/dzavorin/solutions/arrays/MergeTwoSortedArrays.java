package com.dzavorin.solutions.arrays;

public class MergeTwoSortedArrays {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int idx2 = n - 1;
        int idx1 = m - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (idx2 == -1) {
                break;
            } else if (idx1 == -1 || nums2[idx2] >= nums1[idx1]) {
                nums1[i] = nums2[idx2--];
            } else {
                nums1[i] = nums1[idx1--];
            }
        }

    }

}
