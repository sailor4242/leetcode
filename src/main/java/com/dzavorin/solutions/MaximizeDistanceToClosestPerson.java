package com.dzavorin.solutions;

public class MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {

        int j = 0;
        int s = 0;
        int sp = 0;
        int max = 0;
        int k = -10;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                s++;
                max = Math.max(s, max);
                j = i;

                if (j - max == -1 || j == seats.length - 1) {
                    sp = Math.max(sp, s);
                    k = j;
                }

            } else {
                s = 0;
            }
        }

        //  0 0 0 0 1
        // 1 0 0 0 1 0 0 0 1

        if (sp >= max * 2 - 1 || (k - sp == -1 || k == seats.length - 1)) {
            return sp;
        } else if (max == 1) {
            return 1;
        } else {
            return (max % 2 == 0 ? max : max + 1) / 2;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new MaximizeDistanceToClosestPerson().maxDistToClosest(new int[]{1, 0, 0, 0})); // 3
//        System.out.println(new MaximizeDistanceToClosestPerson().maxDistToClosest(new int[]{0,1,1,1,0,0,1,0,0})); // 2
        System.out.println(new MaximizeDistanceToClosestPerson().maxDistToClosest(new int[]{1,1,0,0,0,1,0})); // 2
    }

}
