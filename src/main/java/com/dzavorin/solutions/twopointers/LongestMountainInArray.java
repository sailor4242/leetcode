package com.dzavorin.solutions.twopointers;

public class LongestMountainInArray {

    public int longestMountain(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int res = 0;
        int prevMin = -1;
        int prevMax = -1;

        // dir - direction of current sequence, 1 increasing, 0 stale, -1 decreasing
        int dir = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                if (dir <= 0) {
                    prevMin = i - 1;
                }
                prevMax = i;
                dir = 1;
            } else if (A[i] < A[i - 1]) {
                if (prevMin != -1 && prevMax != -1) {
                    res = Math.max(res, i - prevMin + 1);
                }
                dir = -1;
            } else {
                prevMin = -1;
                prevMax = -1;
                dir = 0;
            }
        }
        return res;
    }

}
