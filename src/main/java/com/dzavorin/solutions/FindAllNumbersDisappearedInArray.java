package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllNumbersDisappearedInArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int n = nums[i];
            while (nums[n - 1] != n) {
                int tmp = nums[n - 1];
                nums[n - 1] = n;
                n = tmp;
            }

        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                res.add(i + 1);
            }
        }

        System.out.println(Arrays.toString(nums));
        return res;
    }


}
