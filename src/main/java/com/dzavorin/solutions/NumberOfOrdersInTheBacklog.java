package com.dzavorin.solutions;

import java.util.*;

public class NumberOfOrdersInTheBacklog {

    private final long MOD = 1000000007L;

    public int getNumberOfBacklogOrders(int[][] orders) {
        TreeMap<Integer, Integer> sell = new TreeMap<>();
        TreeMap<Integer, Integer> buy = new TreeMap<>();
        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int type = order[2];
            if (type == 0) {
                while (amount > 0 && sell.size() > 0) {
                    int k = sell.firstKey();
                    if (k > price)
                        break;
                    int v = sell.firstEntry().getValue();
                    int take = Math.min(amount, v);
                    amount -= take;
                    sell.put(k, v - take);
                    if (v - take == 0)
                        sell.remove(k);
                }
                if (amount > 0)
                    buy.put(price, buy.getOrDefault(price, 0) + amount);
            } else {
                while (amount > 0 && buy.size() > 0) {
                    var entry = buy.lastEntry();
                    int orderPrice = entry.getKey();
                    int orderAmount = entry.getValue();
                    if (orderPrice< price)
                        break;

                    int take = Math.min(amount, orderAmount);
                    amount -= take;
                    buy.put(orderPrice, orderAmount - take);
                    if (orderAmount - take == 0)
                        buy.remove(orderPrice);
                }
                if (amount > 0)
                    sell.put(price, sell.getOrDefault(price, 0) + amount);
            }
        }

        long res = 0L;
        for (long v : buy.values()) {
            res += v;
        }
        for (long v : sell.values()) {
            res += v;
        }
        return (int) (res % MOD);
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfOrdersInTheBacklog().getNumberOfBacklogOrders(new int[][]{
                {7, 1000000000, 1}, {15, 3, 0}, {5, 999999995, 0}, {5, 1, 1}}));

        System.out.println(new NumberOfOrdersInTheBacklog().getNumberOfBacklogOrders(new int[][]{
                {1, 29, 1}, {22, 7, 1}, {24, 1, 0}, {25, 15, 1}, {18, 8, 1}, {8, 22, 0}, {25, 15, 1}, {30, 1, 1}, {27, 30, 0}}));

        System.out.println(new NumberOfOrdersInTheBacklog().getNumberOfBacklogOrders(new int[][]{
                {25, 15, 1}, {25, 15, 1}}));
    }

}
