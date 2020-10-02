package com.dzavorin.solutions.design;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class RecentCounter {

    TreeMap<Integer, Integer> map = new TreeMap<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        Map.Entry<Integer, Integer> entry = map.lastEntry();
        int s;
        if (entry == null) {
            s = 1;
        } else {
            s = entry.getValue() + 1;
        }
        map.put(t, s);

        Map.Entry<Integer, Integer> lower = map.lowerEntry(t - 3000);
        if (lower == null) {
            return s;
        } else {
            return s - lower.getValue();
        }
    }

    class RecentCounter2 {
        LinkedList<Integer> slideWindow;

        public RecentCounter2() {
            this.slideWindow = new LinkedList<Integer>();
        }

        public int ping(int t) {
            // step 1). append the current call
            this.slideWindow.addLast(t);

            // step 2). invalidate the outdated pings
            while (this.slideWindow.getFirst() < t - 3000)
                this.slideWindow.removeFirst();

            return this.slideWindow.size();
        }
    }

}
