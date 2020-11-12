package com.dzavorin.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class Permutations2 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        List<Integer> sans = new ArrayList<>();
        permuteUnique(nums, visited, sans);
        return res;
    }

    public void permuteUnique(int[] nums, boolean[] visited, List<Integer> list) {

        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {

            if (!visited[i] && prev != nums[i]) {
                visited[i] = true;
                prev = nums[i];
                list.add(nums[i]);
                permuteUnique(nums, visited, list);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    //////////////////

    public List<List<Integer>> permuteUnique2(int[] nums) {
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
