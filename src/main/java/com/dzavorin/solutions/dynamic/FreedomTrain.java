package com.dzavorin.solutions.dynamic;

public class FreedomTrain {

    public int findRotateSteps(String ring, String key) {
        Integer[][] memo = new Integer[ring.length()][key.length()];
        return findRotateSteps(ring, key, 0, 0, memo);
    }

    public int findRotateSteps(String ring, String key, int i, int j, Integer[][] memo) {
        if (j == key.length()) {
            return 0;
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        int res = 0;
        int l = i;
        int cl = 0;
        while (true) {
            if (ring.charAt(l) == key.charAt(j)) {
                res = 1 + cl + findRotateSteps(ring, key, l, j + 1, memo);
                break;
            } else {
                cl++;
                l--;
                if (l == -1) {
                    l = ring.length() - 1;
                }
            }
        }

        int m = i;
        int cm = 0;
        while (true) {
            if (ring.charAt(m) == key.charAt(j)) {
                res = Math.min(res, 1 + cm + findRotateSteps(ring, key, m, j + 1, memo));
                break;
            } else {
                cm++;
                m++;
                if (m == ring.length()) {
                    m = 0;
                }
            }
        }

        memo[i][j] = res;

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FreedomTrain().findRotateSteps("godding", "gd"));
        System.out.println(new FreedomTrain().findRotateSteps("godding", "godding"));
    }
}
