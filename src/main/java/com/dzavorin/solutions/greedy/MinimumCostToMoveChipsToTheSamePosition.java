package com.dzavorin.solutions.greedy;

public class MinimumCostToMoveChipsToTheSamePosition {

    //Since move to position[i] + 2 or position[i] - 2 is free,
    // it is natural to think that firstly moving chips as close as possible, with 0 cost.
    // In fact, we can move all chips at even positions to position 0,
    // and move all chips at the odd positions to position 1.

    public int minCostToMoveChips(int[] position) {
        int evenCnt = 0;
        int oddCnt = 0;
        for (int i : position) {
            if (i % 2 == 0) {
                evenCnt++;
            } else {
                oddCnt++;
            }
        }
        return Math.min(oddCnt, evenCnt);
    }

}
