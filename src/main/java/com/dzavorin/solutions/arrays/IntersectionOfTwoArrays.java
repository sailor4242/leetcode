package com.dzavorin.solutions.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;

        Set<Integer> set = new HashSet<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                set.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                i++;
            }
        }


        int[] res = new int[set.size()];
        int k = 0;
        for (Integer n : set) {
            res[k++] = n;
        }
        return res;
    }
}
