package com.dzavorin.solutions.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ReorganizeString {

    public String reorganizeString(String s) {
        char[] counts = new char[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }

        PriorityQueue<char[]> pq = new PriorityQueue<>(Comparator.comparing(arr -> -arr[1]));
        for (char i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                pq.add(new char[]{i, counts[i]});
            }
        }

        StringBuilder sb = new StringBuilder();

        while (pq.size() >= 2) {
            char[] cur = pq.poll();
            char[] next = pq.poll();

            while (next[1] != 0) {
                sb.append((char) (cur[0] + 'a'));
                sb.append((char) (next[0] + 'a'));
                cur[1]--;
                next[1]--;
            }

            if (cur[1] > 0) {
                pq.add(cur);
            }
        }

        char[] last = pq.poll();
        String res = sb.toString();
        if (last != null) {
            if (last[1] > 1) {
                return "";
            } else {
                res = res + (char) (last[0] + 'a');
            }
        }

        return res;
    }

}
