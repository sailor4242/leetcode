package com.dzavorin.solutions.binarysearch;

public class HIndex2 {

    public int hIndex(int[] citations) {

        int lo = 0;
        int hi = citations.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (citations[mid] < citations.length - mid) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return citations.length - lo;
    }

    public static void main(String[] args) {
        System.out.println(new HIndex2().hIndex(new int[]{0, 1, 3, 5, 7}));
        System.out.println(new HIndex2().hIndex(new int[]{11, 15}));
    }
}
