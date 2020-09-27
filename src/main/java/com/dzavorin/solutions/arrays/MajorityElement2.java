package com.dzavorin.solutions.arrays;

import java.util.LinkedList;
import java.util.List;

public class MajorityElement2 {

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new LinkedList<>();

        int n1 = nums[0];
        int n2 = nums[0];
        int c1 = 0;
        int c2 = 0;
        for (int n : nums) {
            if (n1 == n) {
                c1++;
            } else if (n2 == n) {
                c2++;
            } else if (c1 == 0) {
                n1 = n;
                c1 = 1;
            } else if (c2 == 0) {
                n2 = n;
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }

        c1 = 0;
        c2 = 0;

        for (int n : nums) {
            if (n1 == n) {
                c1++;
            } else if (n2 == n) {
                c2++;
            }
        }

        if (c1 > nums.length / 3) {
            res.add(n1);
        }

        if (c2 > nums.length / 3) {
            res.add(n2);
        }

        return res;
    }

}
