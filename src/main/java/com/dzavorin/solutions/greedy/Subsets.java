package com.dzavorin.solutions.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        Set<Set<Integer>> res = new HashSet<>();

        res.add(new HashSet<>(1));

        subsets(nums, res, null);

        return res.stream().map(ArrayList::new).collect(Collectors.toList());
    }

    public void subsets(int[] nums, Set<Set<Integer>> res, Set<Integer> cur) {

        if (res.contains(cur)) {
            return;
        } else if (cur != null) {
            res.add(cur);
        }

        for (int i = 0; i < nums.length; i++) {
            Set<Integer> s;
            if (cur == null) {
                s = new HashSet<>();
                s.add(nums[i]);
                subsets(nums, res, s);
                continue;
            }

            if (!cur.contains(nums[i])) {
                s = new HashSet<>(cur);
                s.add(nums[i]);
                subsets(nums, res, s);
            }
        }

    }

    public List<List<Integer>> subsetsCascading(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList<>();

            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<>(curr) {{
                    add(num);
                }});
            }

            output.addAll(newSubsets);
        }
        return output;
    }


    public List<List<Integer>> subsetsBitMask(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        int n = nums.length;

        for (int i = (int) Math.pow(2, n); i < (int) Math.pow(2, n + 1); i++) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') {
                    curr.add(nums[j]);
                }
            }
            output.add(curr);
        }
        return output;
    }
}
