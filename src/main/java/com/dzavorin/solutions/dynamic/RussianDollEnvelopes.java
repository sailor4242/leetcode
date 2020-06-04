package com.dzavorin.solutions.dynamic;

import java.util.Arrays;
import java.util.Comparator;

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {

        int[] dp = new int[envelopes.length];

        Comparator<int[]> comp = Comparator.<int[], Integer>comparing(c -> c[0]).thenComparing(c -> c[1]);
        Arrays.sort(envelopes, comp);
        Arrays.fill(dp, 1);

        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1] && envelopes[i][0] > envelopes[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return Arrays.stream(dp).max().orElse(0);
    }

    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int[] dp = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new RussianDollEnvelopes().maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
    }

}
