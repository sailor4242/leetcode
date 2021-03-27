package com.dzavorin.solutions.dfs;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(0, numRows, res);

        return res;
    }

    private void dfs(int i, int numRows, List<List<Integer>> res) {
        if (i == numRows) {
            return;
        }

        if (res.isEmpty()) {
            res.add(List.of(1));
        } else {
            List<Integer> prev = res.get(res.size() - 1);
            List<Integer> next = new ArrayList<>();
            next.add(1);
            for (int j = 1; j < prev.size(); j++) {
                next.add(prev.get(j) + prev.get(j - 1));
            }
            next.add(1);
            res.add(next);
        }

        dfs(i + 1, numRows, res);
    }

}
