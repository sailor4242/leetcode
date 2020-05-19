package com.dzavorin.solutions;

public class BestTimeToBuyAndSell2 {
    public int maxProfit(int[] prices) {
        int res = 0;
        int md = 0;
        int l = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (l < prices[i] && prices[i] - l > md) {
                md = Math.max(md, prices[i] - l);
            } else if (l > prices[i] || prices[i] - l < md) {
                res += md;
                md = 0;
                l = prices[i];
            }
        }
        return Math.max(res, md);
    }

    public int maxProfitTrue(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }

    public static void main(String[] args) {
        new BestTimeToBuyAndSell2().maxProfit(new int[]{6,1,3,2,4,7});
    }
}
