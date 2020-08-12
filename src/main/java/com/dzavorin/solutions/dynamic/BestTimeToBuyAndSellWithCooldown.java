package com.dzavorin.solutions.dynamic;

public class BestTimeToBuyAndSellWithCooldown {


    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        return maxProfit(prices, 0, 1, new int[prices.length][prices.length]);
    }

    public int maxProfit(int[] prices, int bId, int sId, int[][] memo) {
        if (bId >= prices.length || sId >= prices.length) {
            return 0;
        }
        if (memo[bId][sId] != 0) {
            return memo[bId][sId];
        }
        if (prices[sId] > prices[bId]) {
            memo[bId][sId] = Math.max(
                    prices[sId] - prices[bId] + maxProfit(prices, sId + 2, sId + 3, memo),
                    maxProfit(prices, bId, sId + 1, memo)
            );
        } else {
            memo[bId][sId] = maxProfit(prices, sId, sId + 1, memo);
        }
        return memo[bId][sId];
    }



    // have no idea how it works
    public int maxProfit3(int[] prices) {

        int previousBuy = Integer.MIN_VALUE;
        int previousSell = 0;
        int buy = Integer.MIN_VALUE;
        int sell = 0;

        for (int price : prices) {
            previousBuy = buy;
            buy = Math.max(buy, previousSell - price);
            previousSell = sell;
            sell = Math.max(sell, previousBuy + price);
        }

        return sell;
    }
}
