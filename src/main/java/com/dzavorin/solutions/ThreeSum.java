package com.dzavorin.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> results = new HashSet<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (nums[i] != nums[i - 1])) {
                int l = i + 1;
                int r = nums.length - 1;

                while (l < r) {
                    if (nums[i] + nums[l] + nums[r] == 0) {
                        List<Integer> triple = new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r]));
                        results.add(triple);
                        while (l < r && nums[l] == nums[l+1]) l++;
                        while (l < r && nums[r] == nums[r-1]) r--;
                        l++;
                        r--;
                    } else if (nums[i] + nums[l] + nums[r] < 0) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }

        return results.stream().collect(Collectors.toList());
    }
}
