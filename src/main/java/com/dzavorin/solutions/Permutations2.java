package com.dzavorin.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class Permutations2 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> list = Arrays.stream(nums)
                .boxed()
                .sorted()
                .collect(Collectors.toList());

        helper(list, 0);
        return res;
    }

    private void helper(List<Integer> list, int idx) {
        if (idx == list.size()) {
            res.add(list);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = idx; i < list.size(); i++) {
            if (!set.add(list.get(i))) {
                continue;
            }

            List<Integer> nextList = new ArrayList<>(list);
            Collections.swap(nextList, i, idx);
            helper(nextList, idx + 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Permutations2().permuteUnique(new int[]{2, 1}));
    }
}
