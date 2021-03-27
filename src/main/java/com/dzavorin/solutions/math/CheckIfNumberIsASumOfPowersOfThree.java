package com.dzavorin.solutions.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckIfNumberIsASumOfPowersOfThree {

    public boolean checkPowersOfThree(int n) {
        //max power of 3
        int maxPower = (int) (Math.log(n) / Math.log(3));

        int[] powers = new int[maxPower + 1];
        powers[0] = 1;
        for (int i = 1; i <= maxPower; i++) {
            powers[i] = powers[i - 1] * 3;
        }

        //Intuition
        //if we subtract n with every power of 3
        //at the end if n is zero, then it is sum of power 3

        //subtract n with power of 3,
        //if n is graeter than power
        for (int i = maxPower; i >= 0; i--) {
            //n is greater/equal to power 3
            if (n >= powers[i]) {
                n -= powers[i];
            }
        }
        return n == 0;
    }

    public boolean checkPowersOfThree2(int n) {
        List<Integer> powers = new ArrayList<>();
        int i = 0;
        while (Math.pow(3, i) <= n) {
            powers.add((int) Math.pow(3, i));
            i++;
        }
        boolean[] visited = new boolean[powers.size()];
        return dp(powers, 0, 0, n, visited, new HashMap<>());
    }

    private boolean dp(List<Integer> powers, int j, int sum, int n, boolean[] visited, Map<Integer, Boolean> memo) {
        if (sum == n) {
            return true;
        } else if (sum > n) {
            return false;
        }

        if (memo.get(sum) != null) return memo.get(sum);

        for (int i = 0; i < powers.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (dp(powers, i, sum + powers.get(i), n, visited, memo)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        memo.put(sum, false);
        return false;
    }

}
