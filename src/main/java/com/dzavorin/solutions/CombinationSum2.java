package com.dzavorin.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> res = new HashSet<>();
        boolean[] visited = new boolean[candidates.length];
        dfs(candidates, target, res, new LinkedList<>(), 0, 0, visited);
        return new ArrayList<>(res);
    }

    private void dfs(int[] candidates, int target,
                     Set<List<Integer>> res,
                     LinkedList<Integer> list, int sum, int begin, boolean[] visited) {

        if (sum > target) {
            return;
        } else if (sum == target) {
            var r = new ArrayList<>(list);
            res.add(r);
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            int c = candidates[i];
            if (sum + c <= target) {
                visited[i] = true;
                list.add(c);
                dfs(candidates, target, res, list, sum + c, i + 1, visited);
                list.removeLast();
                visited[i] = false;
            }
        }
    }
}
