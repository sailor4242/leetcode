package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.List;

public class NumberOfWaysToSplitAString {

    public int numWays(String s) {
        List<Integer> oneIdx = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                oneIdx.add(i);
            }
        }

        if (oneIdx.size() == 0) return (int) ((s.length() - 1L) * (s.length() - 2L) / 2L % (1E9 + 7));

        if (oneIdx.size() % 3 != 0) return 0;

        int third = oneIdx.size() / 3;

        long numPos1 = oneIdx.get(third) - oneIdx.get(third - 1);
        long numPos2 = oneIdx.get(third * 2) - oneIdx.get(third * 2 - 1);

        return (int) ((numPos1 * numPos2) % (1E9 + 7));
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfWaysToSplitAString().numWays("0000")); // 3
        System.out.println(new NumberOfWaysToSplitAString().numWays("10101")); // 4
        System.out.println(new NumberOfWaysToSplitAString().numWays("100101")); // 6
        System.out.println(new NumberOfWaysToSplitAString().numWays("100100010100110")); // 12
        System.out.println(new NumberOfWaysToSplitAString().numWays("1001")); // 0
        System.out.println(new NumberOfWaysToSplitAString().numWays("111")); // 1
        System.out.println(new NumberOfWaysToSplitAString().numWays("1011")); // 2
        System.out.println(new NumberOfWaysToSplitAString().numWays("00000000")); // 21
    }
}
