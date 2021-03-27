package com.dzavorin.solutions.greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumInitsOnATruck {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, Comparator.comparing(box -> -box[1]));

        int units = 0;
        int i = 0;
        while (truckSize > 0 && i < boxTypes.length) {
            int[] box = boxTypes[i];
            int size = Math.min(truckSize, box[0]);
            truckSize -= size;
            units += size * box[1];
            i++;
        }

        return units;
    }

}
