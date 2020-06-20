package com.dzavorin.solutions.greedy;

import java.util.Arrays;

public class HowManyNumbersAreSmallerThanACurrent {

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = new int[100];
        for (Integer n : nums) {
            arr[n]++;
        }
        int s = 0;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            arr[i] = s;
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 < 0) {
                res[i] = 0;
            } else {
                res[i] = arr[nums[i] - 1];
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new HowManyNumbersAreSmallerThanACurrent()
                .smallerNumbersThanCurrent(new int[]{8, 1, 2, 2, 3})));
        System.out.println(Arrays.toString(new HowManyNumbersAreSmallerThanACurrent()
                .smallerNumbersThanCurrent(new int[]{5,0,10,0,10,6})));
    }
}
