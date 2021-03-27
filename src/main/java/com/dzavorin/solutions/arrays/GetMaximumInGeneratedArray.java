package com.dzavorin.solutions.arrays;

public class GetMaximumInGeneratedArray {

    public int getMaximumGenerated(int n) {
        int res = 0;
        for (int i = 0; i <= n; i++) {
            res = Math.max(res, findValue(i));
        }
        return res;
    }

    private static int findValue(int n) {
        int res = 0;
        if (n < 2) {
            res = n;
        } else if (n % 2 == 0) {
            res = findValue(n / 2);
        } else {
            res = findValue(n / 2) + findValue((n / 2) + 1);
        }
        return res;
    }


    ///

    public int getMaximumGenerated2(int n) {
        if (n < 2)
            return n;

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;

        int max = 0;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i / 2];
            if (i % 2 == 1)
                nums[i] += nums[i / 2 + 1];
            max = Math.max(max, nums[i]);
        }

        return max;
    }

}
