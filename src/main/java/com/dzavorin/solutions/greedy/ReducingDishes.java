package com.dzavorin.solutions.greedy;

import java.util.Arrays;

public class ReducingDishes {

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int suffix = 0;
        int curr = 0;
        int res = 0;
        for (int i = satisfaction.length - 1; i >= 0; i--) {
            suffix += satisfaction[i];
            curr = suffix + res;
            if (curr < res) {
                break;
            }
            res = curr;
        }
        return res;
    }

}
