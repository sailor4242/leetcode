package com.dzavorin.solutions.monotonicqueue;

import java.util.Arrays;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] res = new int[nums.length - k + 1];

        if (nums.length < k) {
            return res;
        }

        MonotonicQueue mq = new MonotonicQueue();
        for (int i = 0; i < k - 1; i++) {
            mq.push(nums[i]);
        }
        int j = 0;
        for (int i = k - 1; i < nums.length; i++) {
            mq.push(nums[i]);
            res[j++] = mq.getFirst();
            mq.removeFirst();
        }
        return res;
    }

    public class MonotonicQueue {
        // int[] is of 2 values, 1st - the actual value,
        // 2nd - how many elements were deleted between it and the one before it.
        LinkedList<int[]> mq = new LinkedList<>();

        public void push(int num) {
            int count = 0;
            while (!mq.isEmpty() && mq.getLast()[0] < num) {
                count += mq.getLast()[1] + 1;
                mq.removeLast();
            }
            mq.add(new int[]{num, count});
        }

        public int getFirst() {
            return mq.getFirst()[0];
        }

        public void removeFirst() {
            if (mq.getFirst()[1] > 0) {
                mq.getFirst()[1]--;
            } else {
                mq.removeFirst();
            }
        }
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];

        LinkedList<Integer> list = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!list.isEmpty() && list.peek() < i - k + 1) {
                list.removeFirst();
            }
            // remove smaller numbers in k range as they are useless
            while (!list.isEmpty() && nums[list.peekLast()] < nums[i]) {
                list.pollLast();
            }
            list.add(i);
            if (i >= k - 1) {
                res[j++] = nums[list.peek()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new SlidingWindowMaximum().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));

        System.out.println(Arrays.toString(
                new SlidingWindowMaximum().maxSlidingWindow(new int[]{1, -1}, 1)));
    }

}
