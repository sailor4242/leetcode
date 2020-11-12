package com.dzavorin.solutions;

import java.util.*;

public class CombinationSum4 {

    public int combinationSum4(int[] nums, int target) {
        return dfs(nums, target, new HashMap<>());
    }

    private int dfs(int[] nums, int target, Map<Integer, Integer> memo) {
        if (target == 0) {
            return 1;
        } else if (memo.containsKey(target)) {
            return memo.get(target);
        }

        int res = 0;
        for (int n : nums) {
            if (n <= target) {
                res += dfs(nums, target - n, memo);
            }
        }
        memo.put(target, res);
        return res;
    }


    // Approach 3. Variation, if you have to return all the valid combos instead of just their count.
    // a set to store all possible valid combos
    private Set<List<Integer>> set = new HashSet<>();

    public int combinationSum42(int[] nums, int target) {
        dfs2(nums, target, new ArrayList<>());
        return set.size();
    }

    private void dfs2(int[] nums, int target, ArrayList<Integer> list) {
        if (target == 0) {
            set.add(new ArrayList<>(list));
            return;
        } else if (target < 0) {
            return;
        }

        for (int n : nums) {
            list.add(n);
            dfs2(nums, target - n, list);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum4().combinationSum4(new int[]{4, 2, 1}, 32));
    }

}
