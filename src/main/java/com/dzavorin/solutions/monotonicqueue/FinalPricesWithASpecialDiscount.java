package com.dzavorin.solutions.monotonicqueue;

import java.util.LinkedList;

public class FinalPricesWithASpecialDiscount {

    public int[] finalPrices(int[] prices) {
        MQ mq = new MQ();
        int[] res = new int[prices.length];
        for (int i = prices.length - 1; i >= 0; i--) {
            res[i] = prices[i] - mq.add(prices[i]);
        }

        return res;
    }

    static class MQ {
        LinkedList<Integer> list = new LinkedList<>();

        public int add(int val) {
            while (!list.isEmpty() && list.getLast() > val ) {
                list.removeLast();
            }
            int res = list.isEmpty() ? 0 : list.getLast();
            list.add(val);
            return res;
        }

    }

}
