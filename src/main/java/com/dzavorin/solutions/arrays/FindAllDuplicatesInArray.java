package com.dzavorin.solutions.arrays;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatesInArray {

    public List<Integer> findDuplicates(int[] nums) {

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];

            if (n < 0) {
                n = -n;
            }

            if (nums[n - 1] < 0) {
                res.add(n);
            }
            nums[n - 1] = -nums[n - 1];
        }
        return res;
    }

}
