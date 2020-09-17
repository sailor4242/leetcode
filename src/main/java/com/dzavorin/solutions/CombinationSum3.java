package com.dzavorin.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class CombinationSum3 {

    Set<Set<Integer>> res = new HashSet<>();

    public List<List<Integer>> combinationSum32(int k, int n) {

        dfs(k, n, new HashSet<>(), 0);
        return res.stream().map(set -> new ArrayList<>(set)).collect(Collectors.toList());
    }

    void dfs(int k, int n, Set<Integer> nums, int s) {
        if (s == n && nums.size() == k) {
            res.add(nums);
            return;
        } else if (s > n || nums.size() > k) {
            return;
        }

        for (int i = 1; i <= 9; i++) {

            if (s + i <= n && nums.size() + 1 <= k && !nums.contains(i)) {
                Set<Integer> next = new HashSet<>(nums);
                next.add(i);
                dfs(k, n, next, s + i);
            } else {
                break;
            }
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        this.backtrack(n, k, comb, 0, results);
        return results;
    }

    protected void backtrack(int remain, int k,
                             LinkedList<Integer> comb, int next_start, List<List<Integer>> results) {

        if (remain == 0 && comb.size() == k) {
            results.add(new ArrayList<>(comb));
            return;
        } else if (remain < 0 || comb.size() == k) {
            return;
        }

        for (int i = next_start; i < 9; ++i) {
            comb.add(i + 1);
            this.backtrack(remain - i - 1, k, comb, i + 1, results);
            comb.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum3().combinationSum3(3, 7));
    }

}
