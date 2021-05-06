package com.dzavorin.solutions.amazon;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FindMedianFromDataStream {

    PriorityQueue<Integer> upStream = new PriorityQueue<>();
    PriorityQueue<Integer> downStream = new PriorityQueue<>(Comparator.reverseOrder());


    public FindMedianFromDataStream() {

    }

    public void addNum(int num) {
        double median = findMedian();
        if (median > num) {
            downStream.add(num);
        } else {
            upStream.add(num);
        }
        balance();
    }

    private void balance() {
        while (upStream.size() - 1 > downStream.size()) {
            downStream.add(upStream.poll());
        }
        while (downStream.size() - 1 > upStream.size()) {
            upStream.add(downStream.poll());
        }
    }

    public double findMedian() {
        if (upStream.size() == downStream.size()) {
            if (upStream.size() == 0) return 0d;
            return ((double) upStream.peek() + (double) downStream.peek()) / 2;
        } else {
            return upStream.size() > downStream.size() ? (double) upStream.peek() : (double) downStream.peek();
        }
    }


    //// 2 pointers

    private TreeSet<Double> tset = new TreeSet<>();
    private int n = 0;
    private double mid = 0, base = 0.00001;

    /**
     * initialize your data structure here.
     */

    public void addNum2(int num) {
        double newNum = (double) num + base * n;
        tset.add(newNum);
        if (n == 0) mid = newNum;
        else if (n % 2 == 1) {
            if (newNum < mid) mid = tset.lower(mid);
        } else if (newNum > mid) mid = tset.higher(mid);
        n++;
    }

    public double findMedian2() {
        return (n % 2 == 1) ? (double) (Math.round(mid)) : (double) (Math.round(tset.higher(mid) + mid)) / 2.0;
    }

}
