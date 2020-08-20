package com.dzavorin.solutions.arrays;

import java.util.Arrays;

public class HIndex {

    public int hIndex(int[] citations) {

        Arrays.sort(citations);
        int res = 0;
        for (int i = citations.length - 1; i >= 0; i--) {

            int c = citations[i];
            int h = citations.length - i;
            if (c >= h) {
                res = h;
            }
        }

        return res;
    }

    public int hIndex2(int[] citations) {
        int[] counts = new int[citations.length + 2];
        for (int i = citations.length - 1; i >= 0; i--) {
            counts[Math.min(citations[i], citations.length)]++;
        }

        int h;
        for (h = citations.length; h > 0; h--) {
            counts[h] += counts[h + 1];
            if (counts[h] >= h) break;
        }
        return h;
    }
}
