package com.dzavorin.solutions;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class CountOfSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = nums.length - 1; i >= 0; i--) {

            int m = map.headMap(nums[i], false).values().stream().reduce(Integer::sum).orElse(0);
            res.addFirst(m);

            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        return res;
    }

}
