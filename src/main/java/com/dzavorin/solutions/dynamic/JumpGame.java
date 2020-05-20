package com.dzavorin.solutions.dynamic;

public class JumpGame {

    public boolean canJump(int[] nums) {
        boolean[] pos = new boolean[nums.length];

        if (nums.length == 1) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        } else {
            pos[0] = true;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums[i]; j >= 1; j--) {
                if (i + j < nums.length - 1) {
                    pos[i + j] = true;
                }
            }
        }

        for (int i = 0; i < pos.length - 1; i++) {
            if (!pos[i]) {
                return false;
            }
        }
        return true;

    }

    public boolean canJump2(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] == 0) {
                // check zero is passable
                int j = i - 1;
                while (j >= 0 && nums[j] <= i - j) {
                    j--;
                }
                if (j < 0) return false;
                i = j;
            } else {
                i--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(new JumpGame().canJump(new int[]{3,2,1,0,4}));
//        System.out.println(new JumpGame().canJump(new int[]{0,2,3}));
        System.out.println(new JumpGame().canJump(new int[]{2, 3, 1, 1, 4}));
    }
}
