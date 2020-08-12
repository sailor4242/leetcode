package com.dzavorin.solutions.dynamic;

import java.util.*;

public class AllocateMailboxes {

    private int[][] memo;

    public int minDistance(int[] houses, int k) {
        Arrays.sort(houses);
        memo = new int[houses.length][k + 1];

        int res = go(houses, 0, k);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int go(int[] houses, int idx, int k) {
        if (k == 0 || idx >= houses.length) {
            return Integer.MAX_VALUE;
        }

        if (memo[idx][k] != 0) {
            return memo[idx][k];
        }

        if (k == 1) {
            return getSum(houses, idx, houses.length - 1);
        }

        int min = Integer.MAX_VALUE;

        for (int i = idx; i < houses.length - k + 1; i++) {
            int s = getSum(houses, idx, i);
            int next = go(houses, i + 1, k - 1);
            min = Math.min(min, s + next);
        }

        memo[idx][k] = min;

        return min;
    }

    private int getSum(int[] houses, int from, int to) {
        int m = houses[from + (to - from) / 2];
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += Math.abs(houses[i] - m);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new AllocateMailboxes().minDistance(
                new int[]{1, 4, 8, 10, 20}, 3 // 5
        ));

        System.out.println(new AllocateMailboxes().minDistance(
                new int[]{7, 4, 6, 1}, 1 // 8
        ));

        System.out.println(new AllocateMailboxes().minDistance(
                new int[]{2, 3, 5, 12, 18}, 2 // 9
        ));

        System.out.println(new AllocateMailboxes().minDistance(
                new int[]{3, 6, 14, 10}, 4 // 0
        ));

        System.out.println(new AllocateMailboxes().minDistance(
                new int[]{19, 28, 10, 30, 11, 6, 5, 17}, 4 // 6
        ));

        System.out.println(new AllocateMailboxes().minDistance(
                new int[]{57, 127, 284, 67, 250, 270, 138, 183, 200, 82, 276, 145, 210, 38, 189, 269, 144, 21, 35, 50, 69, 97, 178, 225, 109, 133, 174, 115, 251, 56, 171, 202, 1, 59, 254, 68, 242}, 28 // 13
        ));
    }

}
