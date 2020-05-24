package com.dzavorin.solutions.monotonicqueue;

import java.util.Arrays;
import java.util.LinkedList;

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];

        MonotonicQueue q = new MonotonicQueue();

        for (int i = T.length - 1; i >= 0; i--) {
            res[i] = q.add(T[i], i);
        }

        return res;

    }

    static class MonotonicQueue {

        LinkedList<int[]> list = new LinkedList<>();

        public int add(int n, int i) {
            while (!list.isEmpty() && list.getLast()[0] <= n) {
                list.removeLast();
            }
            int res = 0;
            if (!list.isEmpty()) {
                res = list.getLast()[1] - i;
            }
            list.add(new int[]{n, i});
            return res;
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DailyTemperatures()
                .dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

}
