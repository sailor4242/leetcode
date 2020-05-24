package com.dzavorin.solutions.monotonicqueue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class NextGreaterElement1 {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        MQueue mq = new MQueue();
        int[] res = new int[nums1.length];

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums1.length - 1; i >= 0; i--) {
            map.put(nums1[i], i);
        }

        for (int i = nums2.length - 1; i >= 0; i--) {
            int n = nums2[i];
            if (map.containsKey(n)) {
                res[map.get(n)] = mq.add(n);
            } else {
                mq.add(n);
            }
        }

        return res;

    }

    static class MQueue {

        LinkedList<Integer> list = new LinkedList<>();

        public int add(int n) {
            while (!list.isEmpty() && list.getLast() < n) {
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
        System.out.println(Arrays.toString(new NextGreaterElement1()
                .nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2})));
    }
}
