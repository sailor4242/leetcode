package com.dzavorin.solutions;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int INF = n + 1;

        for (int i = 0; i < n; i++)
            if (nums[i] <= 0)
                nums[i] = INF;

        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]) - 1;
            if (x < n)
                nums[x] = - Math.abs(nums[x]);
        }

        for (int i = 0; i < n; i++)
            if (nums[i] > 0)
                return i + 1;
        return n + 1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[]{4,3,2,1}));
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[]{1,10,1000, 2}));
    }

}
