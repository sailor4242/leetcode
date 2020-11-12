package com.dzavorin.solutions.dynamic;

import java.util.HashMap;

public class BestTimeToBuyAndSell3 {

    ////////////////////////////

    public int maxProfit(int[] prices) {
        HashMap<String, Integer> hm = new HashMap<>();
        return go(0, true, prices, 2, hm);
    }

    public int go(int i, boolean buyOrSell, int[] prices, int k, HashMap<String, Integer> memo) {
        if (k == 0 || i >= prices.length) return 0;

        String str = "" + i + buyOrSell + k;

        if (memo.containsKey(str)) {
            return memo.get(str);
        }

        int res = 0;
        if (buyOrSell) {
            int buy = go(i + 1, false, prices, k, memo) - prices[i];
            int noBuy = go(i + 1, true, prices, k, memo);
            res = Math.max(buy, noBuy);

        } else {
            int sell = go(i + 1, true, prices, k - 1, memo) + prices[i];
            int noSell = go(i + 1, false, prices, k, memo);
            res = Math.max(sell, noSell);
        }

        memo.put(str, res);

        return res;
    }

    //////////////////

    public static int maxProfit4(int[] prices) {
        return go(prices, 0, 0, new Integer[prices.length + 1][3]);
    }

    public static int go(int[] prices, int k, int i, Integer[][] memo) {
        if (i == prices.length || k == 0) {
            return 0;
        }

        if (memo[i][k] != null) {
            return memo[i][k];
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
        memo[i][k] = Math.max(res, go(prices, k, i + 1, memo));

        return memo[i][k];
    }

    // O(n) for 2 transactions only
    public int maxProfit2(int[] prices) {
        if (prices.length == 0)
            return 0;
        int firstBuy = Integer.MIN_VALUE;
        int firstSell = 0;
        int secondBuy = Integer.MIN_VALUE;
        int secondSell = 0;
        for (int i = 0; i < prices.length; i++) {
            firstBuy = Math.max(firstBuy, -prices[i]);
            firstSell = Math.max(firstSell, firstBuy + prices[i]);
            secondBuy = Math.max(secondBuy, firstSell - prices[i]);
            secondSell = Math.max(secondSell, secondBuy + prices[i]);
        }
        return secondSell;
    }

    public static void main(String[] args) {
//        System.out.println(new BestTimeToBuyAndSell3().maxProfit(new int[]{2,1,4}));
        System.out.println(new BestTimeToBuyAndSell3().maxProfit(new int[]{5, 2, 3, 0, 3, 5, 6, 8, 1, 5}));
    }
}
