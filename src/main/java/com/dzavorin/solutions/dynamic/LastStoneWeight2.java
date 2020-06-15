package com.dzavorin.solutions.dynamic;

public class LastStoneWeight2 {

    /**
     * This question eaquals to partition an array into 2 subsets whose difference is minimal
     * (1) S1 + S2  = S
     * (2) S1 - S2 = diff
     * <p>
     * ==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2
     * <p>
     * Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this
     * <p>
     * dp[i][j]   = {true if some subset from 1st to j'th has a sum equal to sum i, false otherwise}
     * i ranges from (sum of all elements) {1..n}
     * j ranges from  {1..n}
     * <p>
     * same as 494. Target Sum
     */

    public int lastStoneWeightII(int[] stones) {
        int S = 0, S2 = 0;
        for (int s : stones) {
            S += s;
        }

        int n = stones.length;
        boolean[][] dp = new boolean[S + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s <= S / 2; s++) {
                if (dp[s][i - 1] || (s >= stones[i - 1] && dp[s - stones[i - 1]][i - 1])) {
                    dp[s][i] = true;
                    S2 = Math.max(S2, s);
                }
            }
        }
        return S - 2 * S2;
    }

    public static void main(String[] args) {
        System.out.println(new LastStoneWeight2().lastStoneWeightII(new int[]{2,7,4,1,8,1}));
        System.out.println(new LastStoneWeight2().lastStoneWeightII(new int[]{1, 2}));
    }
}
