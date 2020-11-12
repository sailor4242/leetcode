package com.dzavorin.solutions.dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BestTimeToBuyAndSellStock4 {

    public static int maxProfit(int k, int[] prices) {
        return go(prices, k, 0, new HashMap<>());
    }

    public static int go(int[] prices, int k, int i, Map<String, Integer> memo) {
        if (i == prices.length || k == 0) {
            return 0;
        }

        String s = i + "-" + k;

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        int res = 0;
        for (int j = i + 1; j < prices.length; j++) {
            if (prices[j] > prices[i]) {
                res = Math.max(
                        res,
                        prices[j] - prices[i] + go(prices, k - 1, j + 1, memo)
                );
            }
        }
        memo.put(s, Math.max(res, go(prices, k, i + 1, memo)));

        return memo.get(s);
    }

    public int maxProfit3(int k, int[] prices) {
        k = Math.min(k, prices.length / 2);
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }

}
