package com.dzavorin.solutions;

import java.util.LinkedList;
import java.util.Queue;

public class MoveZeros {

    public void moveZeroes(int[] nums) {
        LinkedList<Integer> z = new LinkedList<>();
        int l = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                z.add(i);
            } else {
                if (!z.isEmpty()) {
                    Integer r = z.poll();
                }
            }
        }
    }

}
