package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class FourSum2 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0 && B.length == 0 && C.length == 0 && D.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int key = A[i] + B[j];
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        // If the complement (key) exists in the above hashMap, get the frequency and add it to the count variable
        for (int k = 0; k < C.length; k++) {
            for (int l = 0; l < D.length; l++) {
                int key = -(C[k] + D[l]);
                if (map.containsKey(key)) {
                    count += map.get(key);
                }
            }
        }
        return count;
    }
}
