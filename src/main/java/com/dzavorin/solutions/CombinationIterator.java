package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class CombinationIterator {

    int combinationLength;
    String characters;
    int[] indexArray;
    boolean hasnext;

    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;
        this.combinationLength = combinationLength;
        this.hasnext = preparenext();
    }

    boolean preparenext() {
        if (indexArray == null) {
            // initialize array
            indexArray = new int[combinationLength];
            for (int i = 0; i < indexArray.length; i++) {
                indexArray[i] = i;
            }
            return true;
        } else {
            // from last to first, seeking for position can increase
            for (int i = combinationLength - 1; i >= 0; i--) {
                int max = characters.length() - combinationLength + i;
                if (indexArray[i] < max) {
                    // find the one
                    indexArray[i]++;
                    // fill remaining
                    for (int j = i + 1; j < combinationLength; j++) {
                        indexArray[j] = indexArray[i] + (j - i);
                    }
                    return true;
                }
            }
            // not able to find one can increse, return false
            return false;
        }
    }

    public String next() {
        // build the string
        char[] ans = new char[combinationLength];
        for (int i = 0; i < combinationLength; i++) {
            ans[i] = characters.charAt(indexArray[i]);
        }
        // prepare for next
        this.hasnext = preparenext();
        return new String(ans);
    }

    public boolean hasNext() {
        return this.hasnext;
    }


    static class CombinationIteratorAnother {

        int c;
        char[] arr;
        LinkedList<String> list = new LinkedList<>();

        public CombinationIteratorAnother(String characters, int combinationLength) {
            this.c = combinationLength;
            this.arr = characters.toCharArray();

            go(0, combinationLength, new StringBuilder());

        }

        public void go(int lo, int len, StringBuilder sb) {
            if (len == 0) {
                list.add(sb.toString());
                return;
            }
            for (int i = lo; i <= arr.length - len; i++) {
                sb.append(arr[i]);
                go(i + 1, len - 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        public String next() {
            return list.poll();
        }

        public boolean hasNext() {
            return !list.isEmpty();
        }
    }

    public static void main(String[] args) {
        CombinationIterator ci = new CombinationIterator("abc", 2);
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.next());
        System.out.println(ci.hasNext());

        CombinationIteratorAnother cia = new CombinationIteratorAnother("chp", 1);
        System.out.println(cia.next());
        System.out.println(cia.next());
        System.out.println(cia.next());
        System.out.println(cia.hasNext());
    }

}
