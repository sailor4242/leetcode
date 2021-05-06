package com.dzavorin.solutions.design;

public class DesignCircularQueue {

    int[] buffer;
    int writer;
    int reader;
    int k;

    public DesignCircularQueue(int k) {
        this.k = k;
        buffer = new int[k];
        writer = -1;
        reader = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        buffer[++writer % k] = value;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        reader++;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : buffer[reader % k];
    }

    public int Rear() {
        return isEmpty() ? -1 : buffer[writer % k];
    }

    public boolean isEmpty() {
        return writer < reader;
    }

    public boolean isFull() {
        return writer - reader + 1 == k;
    }

}
