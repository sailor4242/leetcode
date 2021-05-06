package com.dzavorin.solutions.amazon;

import java.util.Arrays;

public class SellDeminishingValuedColoredBalls {

    public int maxProfit(int[] inventory, int orders) {
        if (inventory.length == 0) return 0;
        if (inventory.length == 1) {
            int first = Math.max(0, inventory[0] - orders);
            int diff = inventory[0] - first;
            return (int) (diff * (first + (diff % 2 == 0 ? 0.5d + diff / 2: diff / 2)));
        }

        int res = 0;
        Arrays.sort(inventory);

        int count = 1;
        for (int i = inventory.length - 2; i >= 0; i--) {
            int first = inventory[i];
            int second = inventory[i + 1];
            int diff = second - first;

            if (diff == 0) {
                count++;
                continue;
            }

            if (orders > diff * count) {
                res += diff * (first + (diff % 2 == 0 ? 0.5d + diff / 2: (diff + 1) / 2)) * count;
                orders -= diff * count;
                count++;
            } else {
                int mod = orders % count;
                int full = orders / count;

                diff = second - full;
                res += diff * (diff + (diff % 2 == 0 ? 0.5d + diff / 2: diff / 2)) * count;

                diff--;
                res += (diff + (diff % 2 == 0 ? 0.5d + diff / 2: diff / 2)) * mod;
                return res;
            }
        }

        if (orders > 0) {
            int mod = orders % count;
            int full = orders / count;

            int diff = inventory[0] - full;
            res += full * (diff + (diff % 2 == 0 ? 0.5d + diff / 2: diff / 2)) * count;

            if (full != 0) diff--;
            res += (int) (diff % 2 == 0 ? 1 + diff / 2: (diff + 1) / 2) * mod;
            return res;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SellDeminishingValuedColoredBalls().maxProfit(new int[]{2,5}, 4));
    }
}
