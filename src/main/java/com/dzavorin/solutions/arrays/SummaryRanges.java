package com.dzavorin.solutions.arrays;

import java.util.LinkedList;
import java.util.List;

public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {

        List<String> res = new LinkedList<>();

        if (nums.length == 0) return res;

        Integer prev = null;
        Integer start = null;
        for (int n : nums) {

            if (prev == null) {
                prev = n;
                start = n;
            } else if (prev + 1 == n) {
                prev = n;
            } else {
                if (prev.equals(start)) {
                    res.add("" + prev);
                } else {
                    res.add(start + "->" + prev);
                }
                prev = n;
                start = n;
            }
        }

        if (prev.equals(start)) {
            res.add("" + prev);
        } else {
            res.add(start + "->" + prev);
        }

        return res;
    }

}
