package com.dzavorin.solutions.binarysearch;

public class SearchInRotatedSortedArray2 {

    public boolean search(int[] arr, int target) {
        int n = arr.length - 1;
        int lo = 0;
        int hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] == arr[lo] && arr[mid] == arr[hi]) {
                lo++;
                hi--;
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
        return false;
    }

}
