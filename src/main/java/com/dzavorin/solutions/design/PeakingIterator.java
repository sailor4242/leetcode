package com.dzavorin.solutions.design;

import java.util.Iterator;

public class PeakingIterator {

    class PeekingIterator implements Iterator<Integer> {

        Iterator<Integer> it;
        Integer cur = null;
        boolean called = false;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.it = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (!called) {
                cur = it.next();
                called = true;
            }
            return cur;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer res = called ? cur : peek();
            called = false;
            return res;
        }

        @Override
        public boolean hasNext() {
            return called || it.hasNext();
        }
    }

}
