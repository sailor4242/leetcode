package com.dzavorin.solutions.design;

public class HashSetForInts {
    boolean[] arr = new boolean[1000001];

    /** Initialize your data structure here. */
    public HashSetForInts() {

    }

    public void add(int key) {
        if (!contains(key)) {
            arr[key] = true;
        }
    }

    public void remove(int key) {
        if (contains(key)) {
            arr[key] = false;
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return arr[key] != false;
    }
}
