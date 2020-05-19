package com.dzavorin.solutions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SlidingWindowMedian {

    PriorityQueue<Integer> max = new PriorityQueue<>();
    PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.reverseOrder());

    public double[] medianSlidingWindow(int[] nums, int k) {

        double[] res = new double[nums.length - k + 1];

        for (int j = 0; j < k; j++) {
            add(nums[j]);
        }

        int last = nums[0];
        res[0] = getMedian();

        for (int i = k; i < nums.length; i++) {
            add(nums[i]);
            if (getMedian() > last) {
                min.remove(last);
            } else {
                max.remove(last);
            }
            balance();
            res[i + 1 - k] = getMedian();
            last = nums[i + 1 - k];
        }

        return res;
    }

    public void add(int val) {
        if (max.size() == 0) {
            max.add(val);
        } else {
            double med = getMedian();
            if (med > val) {
                min.add(val);
            } else {
                max.add(val);
            }
            balance();
        }
    }

    public void balance() {
        while (max.size() - 1 >= min.size()) {
            min.add(max.poll());
        }

        while (min.size() - 1 >= max.size()) {
            max.add(min.poll());
        }
    }

    public double getMedian() {

        int ma = max.size();
        int mi = min.size();

        if (ma == mi) {
            return (Double.valueOf(max.peek()) + Double.valueOf(min.peek())) / 2;
        } else if (ma > mi) {
            return Double.valueOf(max.peek());
        } else {
            return Double.valueOf(min.peek());
        }
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(
//                new SlidingWindowMedian().medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));

//        System.out.println(Arrays.toString(
//                new SlidingWindowMedian().medianSlidingWindow(new int[]{2147483647,1,2,3,4,5,6,7,2147483647}, 2)));
//
//        System.out.println(Arrays.toString(
//                new SlidingWindowMedian.Solution().medianSlidingWindow(new int[]{2147483647,1,2,3,4,5,6,7,2147483647}, 2)));

        System.out.println(Arrays.toString(new Solution().medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    static class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0) {
                return new double[]{};
            }
            double[] res = new double[nums.length - k + 1];
            TreeSet<Integer> left = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[b], nums[a]));
            TreeSet<Integer> right = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : Integer.compare(nums[a], nums[b]));

            for (int i = 0; i < nums.length; i++) {
                left.add(i);
                right.add(left.pollFirst());
                if (left.size() < right.size()) {
                    left.add(right.pollFirst());
                }

                if (left.size() + right.size() == k) {
                    double median;
                    if (left.size() == right.size()) {
                        median = ((double)nums[left.first() ] + (double)nums[right.first()]) / 2;
                    } else {
                        median = (double)nums[left.first()];
                    }

                    int start = i - k + 1;
                    res[start] = median;
                    if (!left.remove(start)) {
                        right.remove(start);
                    }
                }
            }
            return res;
        }
    }
}
