package com.dzavorin.solutions.monotonicqueue;

import java.util.Arrays;
import java.util.LinkedList;

public class NextGreaterElement2 {

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        int[] nums2 = new int[len*2];
        for (int i = 0; i < len*2; i++) {
            if (i < len) {
                nums2[i] = nums[i];
            } else {
                nums2[i] = nums[i - len];
            }
        }
        MQueue mq = new MQueue();

        for (int j = nums2.length - 1; j >=0 ; j--) {
            int n = nums2[j];
            if (j < len) {
                res[j] = mq.add(n);
            } else {
                mq.add(n);
            }
        }

        return res;
    }

    static class MQueue {

        LinkedList<Integer> list = new LinkedList<>();

        public int add(int n) {

            while (!list.isEmpty() && list.getLast() <= n) {
                list.removeLast();
            }

            int res = -1;
            if (!list.isEmpty()) {
                res = list.getLast();
            }
            list.add(n);

            return res;
        }

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NextGreaterElement2()
                .nextGreaterElements(new int[]{1, 2, 1})));

        System.out.println(Arrays.toString(new NextGreaterElement2()
                .nextGreaterElements(new int[]{5,4,3,2,1})));
    }
}
