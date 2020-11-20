package com.dzavorin.solutions.binarysearch;

public class SearchRotatedSortedArray {

    public int search(int[] arr, int target) {
        int n = arr.length - 1;
        int lo = 0;
        int hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] >= arr[lo]) {
                if (target >= arr[lo] && target <= arr[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target <= arr[hi] && target >= arr[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchRotatedSortedArray().search(new int[]{4, 5, 6, 7, 8, 0, 1, 2}, 0));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{}, 5));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{1}, 1));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{3, 1}, 1));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{1, 3, 5}, 0));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{1, 3, 5}, 1));
        System.out.println(new SearchRotatedSortedArray().search(new int[]{3, 5, 1}, 5));
    }
}
