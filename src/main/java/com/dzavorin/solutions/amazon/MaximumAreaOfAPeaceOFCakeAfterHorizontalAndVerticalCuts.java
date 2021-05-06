package com.dzavorin.solutions.amazon;

import java.util.Arrays;

public class MaximumAreaOfAPeaceOFCakeAfterHorizontalAndVerticalCuts {

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxH = Math.max(h - horizontalCuts[horizontalCuts.length - 1], horizontalCuts[0]);
        for (int i = 1; i < horizontalCuts.length; i++) {
            maxH = Math.max(maxH, horizontalCuts[i] - horizontalCuts[i - 1]);
        }

        int maxV = Math.max(w - verticalCuts[verticalCuts.length - 1], verticalCuts[0]);
        for (int i = 1; i < verticalCuts.length; i++) {
            maxV = Math.max(maxV, verticalCuts[i] - verticalCuts[i - 1]);
        }

        return (int)(((long) maxH * (long) maxV) % ((int) 1E9 + 7));
    }
}
