package com.dzavorin.solutions.arrays;

public class BulbSwitcher3 {

    public int numTimesAllBlue(int[] light) {

        int count = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < light.length; i++) {
            max = Math.max(max, light[i] - 1);
            if (max == i) {
                count++;
            }
        }
        return count;
    }

    public int numTimesAllBlue2(int[] light) {

        int max = 0;
        int count = 0;
        boolean[] on = new boolean[light.length];
        for (int i = 0; i < light.length; i++) {

            on[i] = true;
            max = Math.max(light[i] - 1, max);

            boolean all = true;
            for (int j = max; j >= 0; j--) {
                if (!on[j]) {
                    all = false;
                    break;
                }
            }
            if (all) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new BulbSwitcher3().numTimesAllBlue(new int[]{2, 1, 3, 5, 4})); // 3
        System.out.println(new BulbSwitcher3().numTimesAllBlue(new int[]{3, 2, 4, 1, 5})); // 2
    }
}
