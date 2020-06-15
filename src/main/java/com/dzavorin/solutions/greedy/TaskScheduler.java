package com.dzavorin.solutions.greedy;

import java.util.*;

public class TaskScheduler {

    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }

        Arrays.sort(freq);

        int max = freq[25] - 1;
        int idleSlots = max * n;

        for (int i = 24; i >= 0 && freq[i] > 0; i--) {
            idleSlots -= Math.min(freq[i], max);
        }

        return idleSlots > 0 ? idleSlots + tasks.length : tasks.length;
    }
}
