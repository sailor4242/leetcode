package com.dzavorin.solutions.arrays;

import java.util.ArrayList;
import java.util.TreeSet;

public class ShortestDistanceToACharacter {

    public int[] shortestToChar(String s, char c) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == c) {
                list.add(i);
            }
        }

        int j = 0;
        int[] res = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != c) {
                if (i < list.get(j)) {
                    res[i] = list.get(j) - i;
                } else {
                    if (j < list.size() - 1 && i - list.get(j) > list.get(j + 1) - i) {
                        j++;
                        res[i] = list.get(j) - i;
                    } else {
                        res[i] = i - list.get(j);
                    }
                }
            } else {
                if (list.get(j) < i) {
                    j++;
                }
            }
        }

        return res;
    }

    ///

    public int[] shortestToChar2(String S, char C) {
        int N = S.length();
        int[] ans = new int[N];
        int prev = Integer.MIN_VALUE / 2;

        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = i - prev;
        }

        prev = Integer.MAX_VALUE / 2;
        for (int i = N - 1; i >= 0; i--) {
            if (S.charAt(i) == C) prev = i;
            ans[i] = Math.min(ans[i], prev - i);
        }

        return ans;
    }

    ////

    public int[] shortestToChar3(String S, char C) {
        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                set.add(i);
            }
        }

        int[] result = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            if (!set.contains(i)) {

                Integer left = set.floor(i);
                Integer right = set.ceiling(i);

                if (left == null && right != null) {
                    result[i] = right - i;
                } else if (right == null && left != null) {
                    result[i] = i - left;
                } else {
                    result[i] = Math.min(i - left, right - i);
                }
            }
        }
        return result;
    }

}
