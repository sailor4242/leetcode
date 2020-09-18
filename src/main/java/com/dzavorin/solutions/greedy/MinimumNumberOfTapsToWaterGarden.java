package com.dzavorin.solutions.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfTapsToWaterGarden {

    public int minTaps(int n, int[] ranges) {
        if (ranges.length == 0) return -1;

        int[][] arr = new int[ranges.length][3];

        for (int i = 0; i < ranges.length; i++) {
            arr[i][0] = i;
            arr[i][1] = Math.max(i - ranges[i], 0);
            arr[i][2] = Math.min(i + ranges[i], n);
        }

        Arrays.sort(arr, Comparator.<int[], Integer>comparing(a -> a[1]).thenComparing(a -> -(a[2] - a[1])));
        int res = 1;

        int leftF = arr[0][1];
        int rightF = arr[0][2];

        if (leftF > 0) return -1;
        if (leftF <= 0 && rightF >= n) return res;


        int soFar = rightF;
        for (int i = 1; i < arr.length; i++) {

            if (soFar >= n) {
                return res;
            }

            int[] ans = mostRight(arr, i, soFar);
            if (ans[0] == -1) {
                return -1;
            } else {
                i = ans[0];
                soFar = ans[1];
                res++;
            }

        }

        return res;
    }

    int[] mostRight(int[][] arr, int i, int soFar) {
        int[] ans = new int[2];
        ans[0] = i;
        ans[1] = soFar;
        boolean notFound = true;
        for (int j = i; j < arr.length; j++) {

            int left = arr[j][1];
            int right = arr[j][2];

            if (left <= soFar && right > ans[1]) {
                notFound = false;
                ans[0] = j;
                ans[1] = right;
            }

        }
        return notFound ? new int[] {-1, -1} : ans;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfTapsToWaterGarden().minTaps(5, new int[]{3,4,1,1,0,0}));
        System.out.println(new MinimumNumberOfTapsToWaterGarden().minTaps(3, new int[]{0,0,0,0}));
    }
}
