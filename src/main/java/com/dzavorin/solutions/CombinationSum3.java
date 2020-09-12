package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CombinationSum3 {

    Set<Set<Integer>> res = new HashSet<>();

    public List<List<Integer>> combinationSum3(int k, int n) {

        dfs(k, n, new HashSet<>(), 0);
        return res.stream().map(set -> new ArrayList<>(set)).collect(Collectors.toList());
    }

    void dfs(int k, int n, Set<Integer> nums, int s) {
        if (s == n && nums.size() == k) {
            res.add(nums);
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

    public static void main(String[] args) {
        System.out.println(new CombinationSum3().combinationSum3(3, 7));
    }

}
