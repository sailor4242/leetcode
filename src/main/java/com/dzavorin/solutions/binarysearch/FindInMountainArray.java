package com.dzavorin.solutions.binarysearch;

public class FindInMountainArray {

    public int findInMountainArray(int target, int[] mountainArr) {

        int lo = 0;
        int hi = mountainArr.length - 1;

        while (lo < hi) {

            int m = lo + (hi - lo) / 2;
            int mm = mountainArr[m];

            if (mm > mountainArr[m + 1]) {
                hi = m;
            } else {
                lo = m + 1;
            }
        }

        int max = lo;
        int min = Integer.MAX_VALUE;

        lo = 0;
        hi = max;
        while (lo <= hi) {

            int m = lo + (hi - lo) / 2;
            int mm = mountainArr[m];

            if (mm == target) {
                min = Math.min(min, m);
                hi = m - 1;
            } else if (target < mm) {
                hi = m - 1;
            } else {
                lo = m + 1;
            }
        }

        lo = max;
        hi = mountainArr.length - 1;
        while (lo <= hi) {

            int m = lo + (hi - lo) / 2;
            int mm = mountainArr[m];

            if (mm == target) {
                min = Math.min(min, m);
                lo = m + 1;
            } else if (mm < target) {
                hi = m - 1;
            } else {
               lo = m + 1;
            }
        }

        return min >= mountainArr.length ? -1 : min;
    }

    public static void main(String[] args) {
        System.out.println(new FindInMountainArray().findInMountainArray(3, new int[]{1,2,3,4,5,3,1}));
        System.out.println(new FindInMountainArray().findInMountainArray(2, new int[]{1,2,3,4,5,3,1}));
        System.out.println(new FindInMountainArray().findInMountainArray(1, new int[]{1,5,2}));
    }

}
