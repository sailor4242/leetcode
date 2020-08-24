package com.dzavorin.solutions.twopointers;

import java.util.Arrays;

public class BoatsToSavePeople {

    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        int i = 0;
        int j = people.length - 1;
        int res = 0;

        while (i < j) {
            int wi = people[i];
            int wj = people[j];

            if (wi + wj <= limit) {
                i++;
            }

            j--;
            res++;
        }

        return res + (i == j ? 1 : 0);
    }

}
