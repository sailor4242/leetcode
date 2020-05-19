package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.List;

public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        List<Integer> l = new ArrayList<>();
        int z = 0;
        int e = 0;

        for (int i = 0; i < nums.length; i++) {
            boolean c = i - 1 >= 0 && nums[i - 1] != nums[i];
            if (nums[i] == 0) {
                if (c) {
                    l.add(e);
                    e = 0;
                }
                z++;
            } else if (nums[i] == 1) {
                if (c) {
                    l.add(z);
                    z = 0;
                }
                e++;
            }
        }

        if (z == 0) {
            l.add(e);
        } else {
            l.add(z);
        }

        int m = 0;

        for (int i = 0; i < l.size(); i++) {

            int j = 0;
            if ( i - 1 >= 0) {
                j += l.get(i - 1);
            }
            if ( i + 1 < l.size()) {
                j += l.get(i + 1);
            }

            Integer n = l.get(i);
            if (j >= n) {
                m = Math.max(m, n);
            }
        }

        return m;

    }

    public static void main(String[] args) {
        System.out.println(new ContiguousArray().findMaxLength(new int[] {0, 1}));
    }

//    public int findMaxLength2(int[] nums) {
//
//        for (int i = 0; i < nums.size(); i++) {
//            if (nums[i] == 0) {
//                check(i, nums);
//                z++;
//                if ( z == e) {
//                    l = Math.max(l, z + e);
//                }
//            } else if (nums[i] == 1) {
//                check(i, nums);
//                e++;
//                if ( z == e) {
//                    l = Math.max(l, z + e);
//                }
//            }
//        }
//        return l;
//    }
//
//    public void check(int i, int[] nums) {
//        boolean c = i - 1 > 0 && nums[i - 1] != nums[i];
//        boolean cz = c && nums[i] == 0;
//        boolean ce = c && nums[i] == 1;
//
//        if (cz) {
//            z = 0;
//        } else if (ce) {
//            e = 0;
//        }
//
//    }
}
