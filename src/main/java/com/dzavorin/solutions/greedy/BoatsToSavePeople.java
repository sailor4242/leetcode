package com.dzavorin.solutions.greedy;

import java.util.Arrays;

public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int lo = 0;
        int hi = people.length - 1;
        int res = 0;

        while (lo <= hi) {

            if (people[hi] + people[lo] <= limit) {
                lo++;
            }
            hi--;
            res++;
        }

        return res;
    }

}
