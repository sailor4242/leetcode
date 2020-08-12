package com.dzavorin.solutions.bits;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber2 {


    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, not_threes = 0;
        for (int n : nums) {
            twos |= (ones & n);
            ones ^= n;
            not_threes = ~(ones & twos);
            ones &= not_threes;
            twos &= not_threes;
        }

        return ones;
    }


    public int singleNumber2(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap();
        for (int num : nums)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1)
                return entry.getKey();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new SingleNumber2().singleNumber(new int[]{-2, -2, 1, 1, -3, 1, -3, -3, -4, -2}));
        System.out.println(new SingleNumber2().singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
        System.out.println(new SingleNumber2().singleNumber(new int[]{2, 2, 3, 2}));
    }
}


