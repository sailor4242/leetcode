package com.dzavorin.solutions.design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlattenNestedListIterator {

    public static class NestedIterator implements Iterator<Integer> {

        LinkedList<NestedInteger> nestedList;
        Integer prev;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = new LinkedList<>(nestedList);
            this.prev = getNext();
        }


        @Override
        public Integer next() {
            Integer res = prev;
            this.prev = getNext();
            return res;
        }

        private Integer getNext() {
            if (!hasNextHelper()) return null;

            NestedInteger nint = nestedList.removeFirst();
            if (nint.isInteger()) {
                return nint.getInteger();
            } else {
                List<NestedInteger> list = nint.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    nestedList.addFirst(list.get(i));
                }
                return getNext();
            }
        }

        @Override
        public boolean hasNext() {
            return prev != null;
        }

        private boolean hasNextHelper() {
            return nestedList.size() != 0;
        }
    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }
}
