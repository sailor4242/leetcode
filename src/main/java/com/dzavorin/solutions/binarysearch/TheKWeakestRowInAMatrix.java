package com.dzavorin.solutions.binarysearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TheKWeakestRowInAMatrix {

    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.<int[], Integer>comparing(arr -> arr[1])
                        .thenComparing(arr -> arr[0]));

        for (int i = 0; i < mat.length; i++) {
            pq.add(new int[]{i, bsearch(mat[i])});
        }

        int[] res = new int[k];
        int n = k;
        while (k > 0) {
            res[n - k] = pq.poll()[0];
            k--;
        }

        return res;
    }

    private int bsearch(int[] arr) { // [1,1,0,0] // 2
        int lo = 0;
        int hi = arr.length - 1; // 3

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] == 1) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    public static void main(String[] args) {
        System.out.println(new TheKWeakestRowInAMatrix().bsearch(new int[]{0,0,0,0}));
        System.out.println(Arrays.toString(new TheKWeakestRowInAMatrix().kWeakestRows(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}}, 3)));
    }
}
