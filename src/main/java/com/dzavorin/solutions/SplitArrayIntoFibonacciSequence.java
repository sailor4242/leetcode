package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {

    public List<Integer> splitIntoFibonacci(String S) {
        return dfs(S, 0, new ArrayList<>());
    }

    private List<Integer> dfs(String s, int start, List<Integer> list) {
        if (start == s.length()) {
            return new ArrayList<>(list);
        }

        long cur = 0;
        for (int i = start; i < s.length(); i++) {
            cur = (cur * 10L) + (long)(s.charAt(i) - '0');

            if (cur > Integer.MAX_VALUE) {
                return new ArrayList<>();
            }

            if (list.size() < 2 || list.get(list.size() - 1) + list.get(list.size() - 2) == (int)cur) {
                list.add((int) cur);
                List<Integer> next = dfs(s, i + 1, list);
                if (next.size() > 2) {
                    return next;
                }
                list.remove(list.size() - 1);
            }

            if (cur == 0) {
                break;
            }
        }

        return new ArrayList<>();
    }

}
