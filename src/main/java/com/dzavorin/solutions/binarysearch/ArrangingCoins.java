package com.dzavorin.solutions.binarysearch;

public class ArrangingCoins {

    public int arrangeCoins(int n) {
        long left = 0, right = n;
        long k, curr;

        while (left <= right) {
            k = left + (right - left) / 2;
            curr = k * (k + 1) / 2;

            if (curr == n) {
                return (int) k;
            }

            if (n < curr) {
                right = k - 1;
            } else {
                left = k + 1;
            }
        }
        return (int) right;
    }

    // https://leetcode.com/articles/arranging-coins/

    public int arrangeCoins3(int n) {
        return (int) (Math.sqrt(2 * (long) n + 0.25) - 0.5);
    }

    public int arrangeCoins2(int n) {
        int rows = 0;
        int m = n;
        for (int i = 1; i <= n; i++) {
            m -= i;
            if (m >= 0) {
                rows++;
            } else {
                break;
            }
        }
        return rows;
    }

}
