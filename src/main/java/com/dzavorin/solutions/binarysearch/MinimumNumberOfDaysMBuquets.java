package com.dzavorin.solutions.binarysearch;


public class MinimumNumberOfDaysMBuquets {

    public int minDays(int[] bloomDays, int m, int k) {
        int n = bloomDays.length;
        if (m * k > n) {
            return -1;
        }

        int left = 1;
        int right = (int) 1e9;

        while (left < right) {
            int mid = (left + right) / 2;
            int flow = 0;
            int bouq = 0;
            for (int day : bloomDays) {
                if (day > mid) {
                    flow = 0;
                } else if (++flow >= k) {
                    bouq++;
                    flow = 0;
                }
            }
            if (bouq < m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfDaysMBuquets().minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
        System.out.println(new MinimumNumberOfDaysMBuquets().minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }
}
