package com.dzavorin.solutions;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

public class Permutations {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        permute(nums, new LinkedHashSet<>());
        return res;
    }

    public void permute(int[] nums, Set<Integer> exclude) {
        if (exclude.size() == nums.length) {
            res.add(new ArrayList<>(exclude));
            return;
        }
        for (Integer i : nums) {
            if (!exclude.contains(i)) {
                exclude.add(i);
                permute(nums, exclude);
                exclude.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Permutations().permute(new int[]{1,2,3}));
    }

}
