package com.dzavorin.solutions.design;

public class DesignStackWithIncrementOperation {

    int[] st;
    int max;


    public DesignStackWithIncrementOperation(int maxSize) {
        st = new int[maxSize];
        max = 0;

    }

    public void push(int x) {
        if (max == st.length) return;
        st[max++] = x;
    }

    public int pop() {
        if (max == 0) return -1;
        return st[--max];
    }

    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, max); i++) {
            st[i] = st[i] + val;
        }
    }
}
