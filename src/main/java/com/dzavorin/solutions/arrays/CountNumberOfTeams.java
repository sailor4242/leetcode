package com.dzavorin.solutions.arrays;

public class CountNumberOfTeams {

    public int numTeams(int[] rating) {
        if (rating.length < 3) return 0;

        return numTeams(rating, 0, 0, 3, true)
                + numTeams(rating, 0, Integer.MAX_VALUE, 3, false);
    }

    public int numTeams(int[] rating, int i, int prev, int k, boolean inc) {

        if (k == 0) {
            return 1;
        }

        int res = 0;
        for (;i < rating.length; i++) {

            if (inc && rating[i] > prev && k - 1 >= 0) {
                res += numTeams(rating, i + 1, rating[i], k - 1, true);

            } else if (!inc && rating[i] < prev && k - 1 >= 0) {
                res += numTeams(rating, i + 1, rating[i], k - 1, false);
            }

        }

        return res;

    }

}
