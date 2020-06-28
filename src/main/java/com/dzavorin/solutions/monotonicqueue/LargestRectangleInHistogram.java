package com.dzavorin.solutions.monotonicqueue;

import java.util.LinkedList;

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        MQ mq = new MQ();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            max = Math.max(max, mq.add(i < heights.length ? heights[i] : 0, i));
        }

        return max;

    }

    static class MQ {

        LinkedList<int[]> list = new LinkedList<>();

        public int add(int val, int i) {
            int max = 0;
            while (!list.isEmpty() && val < list.getLast()[0]) {

                int[] cur = list.removeLast();

                int leftBoundaryIndex = !list.isEmpty() ? list.getLast()[1] : -1;

                int width = i - leftBoundaryIndex - 1;

                max = Math.max(max, width * cur[0]);
            }

            list.add(new int[]{val, i});
            return max;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LargestRectangleInHistogram()
                .largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }

}
