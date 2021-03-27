package com.dzavorin.solutions.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticSlices {

    /////// math formula

    public int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        if (A.length < 3) return res;

        int count = 0;
        int diff = A[1] - A[0];

        for (int i = 2; i < A.length; i++) {
            int nextDiff = A[i] - A[i - 1];
            if (nextDiff != diff) {
                diff = nextDiff;
                res += (count + 1) * (count) / 2;
                count = 0;
            } else {
                count++;
            }
        }

        res += (count + 1) * (count) / 2;

        return res;
    }

    ///////// dp

    public int numberOfArithmeticSlices2(int[] A) {
        int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < dp.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }
        return sum;
    }

    /////////

    Map<Integer, Integer> memo = new HashMap<>();

    public int numberOfArithmeticSlices3(int[] A) {

        if (A.length < 3) return 0;

        List<Integer> list = new ArrayList<>();

        int diff = A[1] - A[0];
        int c = 2;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] != diff) {
                if (c >= 3) {
                    list.add(c);
                }
                diff = A[i] - A[i - 1];
                c = 2;
            } else {
                c++;
            }
        }
        if (c >= 3) {
            list.add(c);
        }

        memo.put(3, 1);

        int res = 0;
        for (int n : list) {
            res += calculateSeq(n);
        }
        return res;
    }

    private int calculateSeq(int n) {
        if (memo.containsKey(n)) return memo.get(n);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int c = 0;
            for (int j = i; j < n; j++) {
                c++;
                if (c >= 3) {
                    res++;
                }
            }
        }
        return res;
    }
}
