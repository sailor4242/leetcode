package com.dzavorin.solutions;

import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {

    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.max = new PriorityQueue<>();
        this.min = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        int ma = max.size();
        int mi = min.size();

        if (ma == 0) {
            max.add(num);
        } else {
            double med = findMedian();
            if (med > num) {
                min.add(num);
            } else {
                max.add(num);
            }
            balance();
        }
    }

    private void balance() {
        while (max.size() - 1 >= min.size()) {
            min.add(max.poll());
        }
        while (min.size() - 1 >= max.size()) {
            max.add(min.poll());
        }

    }

    public double findMedian() {
        int ma = max.size();
        int mi = min.size();

        if (ma == mi) {
            int maPeek = max.peek();
            int miPeek = min.peek();
            return (Double.valueOf(maPeek) + Double.valueOf(miPeek)) / 2;
        } else if (ma > mi) {
            return Double.valueOf(max.peek());
        } else {
            return Double.valueOf(min.peek());
        }
    }
}
