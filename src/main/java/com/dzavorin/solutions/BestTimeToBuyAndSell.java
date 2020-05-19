package com.dzavorin.solutions;

public class BestTimeToBuyAndSell {

    public int maxProfit(int[] prices) {

        if (prices.length < 2) {
            return 0;
        }

        int res = 0;
        int l = prices[0];
        int arr[] = new int[prices.length];

        for (int i = 1; i < prices.length; i++) {
            if ( l < prices[i] && prices[i] - l > res) {
                res = prices[i] - l;
            } else if (l > prices[i]) {
                l = prices[i];
            }
        }
        return res;
    }

}
