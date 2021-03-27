package com.dzavorin.solutions;

import java.util.LinkedList;

public class FindTheMostCompetitiveSubsiquence {

    public int[] mostCompetitive(int[] nums, int K) {

        LinkedList<Integer> list = new LinkedList<>();

        int k = nums.length - K;

        for (int n : nums) {

            while (!list.isEmpty() && k > 0 && list.getLast() > n) {

                list.removeLast();
                k--;
            }
            list.add(n);

        }

        while (k > 0) {
            list.removeLast();
            k--;
        }

        int[] res = new int[K];
        int i = K - 1;
        while (!list.isEmpty()) {
            res[i--] = list.removeLast();
        }

        return res;
    }


}
