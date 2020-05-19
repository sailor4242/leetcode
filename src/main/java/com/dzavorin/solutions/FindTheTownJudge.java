package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindTheTownJudge {

    public int findJudge(int N, int[][] trust) {
        if (trust.length == 0) {
            return N;
        }

        Map<Integer, Integer> trusted = new HashMap<>();
        Set<Integer> trusts = new HashSet<>();

        for (int i = 0; i < trust.length; i++) {
            trusts.add(trust[i][0]);
            trusted.compute(trust[i][1], (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        }

        int[] arr = new int[]{-1};
        trusted.forEach((k, v) -> {
            if (v == trusts.size() && !trusts.contains(k)) {
                arr[0] = k;
            }
        });

        return arr[0];

    }

    public static void main(String[] args) {
        System.out.println(new FindTheTownJudge().findJudge(2, new int[][]{{1, 2}}));
    }
}
