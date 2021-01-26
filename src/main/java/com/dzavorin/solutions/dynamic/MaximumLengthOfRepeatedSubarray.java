package com.dzavorin.solutions.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumLengthOfRepeatedSubarray {

    /// bruteforce

    public int findLength(int[] A, int[] B) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < B.length; i++) {
            map.putIfAbsent(B[i], new ArrayList<>());
            map.get(B[i]).add(i);
        }

        int res = 0;
        for (int i = 0; i < A.length; i++) {
            List<Integer> idxs = map.get(A[i]);
            if (idxs != null) {
                for (int j : idxs) {
                    int cnt = 0;
                    int k = i;
                    for (; j < B.length; j++) {
                        if (k < A.length && A[k] == B[j]) {
                            cnt++;
                            res = Math.max(res, cnt);
                            k++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        return res;
    }

    //// dp

    public int findLength2(int[] a, int[] b) {
        int res = 0;
        int[] dp = new int[b.length + 1];
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = 0; j < b.length; j++) {
                dp[j] = a[i] == b[j] ? dp[j + 1] + 1 : 0;
                res = Math.max(dp[j], res);
            }
        }
        return res;
    }

}
