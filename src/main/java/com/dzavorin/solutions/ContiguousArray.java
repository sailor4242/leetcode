package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                sum++;
            } else {
                sum--;
            }

            if (sum == 0) {
                res = Math.max(i + 1, res);
            } else {
                Integer m = map.get(sum);
                if (m == null) {
                    map.put(sum, i);
                } else {
                    res = Math.max(res, i - m);
                }
            }

        }

        return res;


    }

    public static void main(String[] args) {
        System.out.println(new ContiguousArray().findMaxLength(new int[]{0, 1}));
    }

}
