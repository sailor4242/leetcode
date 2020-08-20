package com.dzavorin.solutions.dynamic;

import java.util.HashMap;

public class BestTimeToBuyAndSell3 {

    public int maxProfit3(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[][][] memo = new int[prices.length][prices.length][3];
        int r = go(prices, 0, 1, 2, memo);
        return r;
    }

    private int go(int[] prices, int i, int j, int k, int[][][] memo) {

        if (k == 0 || j >= prices.length) {
            return 0;
        }

        if (memo[i][j][k] != 0) {
            return memo[i][j][k];
        }
        int res;
        if (prices[j] > prices[i]) {
            int r = prices[j] - prices[i];
            int next = go(prices, j + 1, j + 2, k - 1, memo);
            int skip = go(prices, i, j + 1, k, memo);
            memo[i][j][k] = Math.max(r + next, skip);
        } else {
            int next = go(prices, j, j + 1, k, memo);
            int skip = go(prices, i, j + 1, k, memo);
            memo[i][j][k] = Math.max(next, skip);
        }

        return memo[i][j][k];
    }

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
        return maxProfitHelper(prices, 0, 0, new Integer[prices.length + 1][3]);
    }

    public static int maxProfitHelper(int[] prices, int tr, int i, Integer[][] memo) {
        if (i == prices.length || tr == 2) {
            return 0;
        }

        if (memo[i][tr] != null) {
            return memo[i][tr];
        }

        int current = 0;

        for (int j = i + 1; j < prices.length; j++) {
            if (prices[i] < prices[j]) {
                int price = prices[j] - prices[i];

                int recur = maxProfitHelper(prices, tr + 1, j + 1, memo);

                current = Math.max(current, price + recur);
            }
        }
        current = Math.max(current, maxProfitHelper(prices, tr, i + 1, memo));

        memo[i][tr] = current;
        return memo[i][tr];
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
