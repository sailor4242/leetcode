package com.dzavorin.solutions.greedy;

import java.util.Arrays;

public class SelfDeminishingValuedColoredBalls {

    public int maxProfit(int[] inventory, int orders) {

        int mod = 1000000007;
        Arrays.sort(inventory);
        int i = inventory.length - 1;
        int cur = inventory[i];
        long res = 0;

        while (orders > 0) {
            while (i >= 0 && inventory[i] == cur) {
                i--;
            }
            // if all colors of balls are the same value, next is 0
            int next = i < 0 ? 0 : inventory[i];
            // number of colors of balls with same value
            int count = inventory.length - 1 - i;
            // number of items to buy
            int nums = (cur - next) * count;
            if (orders >= nums) {
                // buy all items
                res += (long) (cur + next + 1) * (cur - next) / 2 * count;
            } else {
                // orders left is less than the number of items to buy
                int numFullRow = orders / count;
                int remainder = orders % count;
                res += (long)(cur + cur - numFullRow + 1) * numFullRow / 2 * count;
                res += (long)(cur - numFullRow) * remainder;
            }
            orders -= nums;
            res = res % mod;
            cur = next;
        }

        return (int) res;
    }

}
