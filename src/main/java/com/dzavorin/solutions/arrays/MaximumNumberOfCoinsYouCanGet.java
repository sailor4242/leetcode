package com.dzavorin.solutions.arrays;

import java.util.Arrays;

public class MaximumNumberOfCoinsYouCanGet {

    public int maxCoins(int[] piles) {
        if (piles.length % 3 != 0) return 0;
        Arrays.sort(piles);

        int i = piles.length - 1;
        int j = 0;
        int res = 0;
        while (j < i) {
            res += piles[i - 1];
            i -= 2;
            j++;
        }
        return res;
    }

}
