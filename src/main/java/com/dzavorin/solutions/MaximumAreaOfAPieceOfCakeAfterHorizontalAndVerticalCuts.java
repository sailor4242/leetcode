package com.dzavorin.solutions;

import java.util.Arrays;

public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {

    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int maxH = 0;
        int prevH = 0;
        for (int horCut : horizontalCuts) {
            maxH = Math.max(maxH, horCut - prevH);
            prevH = horCut;
        }

        maxH = Math.max(maxH, h - prevH);


        int maxW = 0;
        int prevW = 0;
        for (int verCut : verticalCuts) {
            maxW = Math.max(maxW, verCut - prevW);
            prevW = verCut;
        }
        maxW = Math.max(maxW, w - prevW);

        long res = (long) maxH * (long) maxW;
        res %= (int) 1E9 + 7;
        return (int) res;
    }

}
