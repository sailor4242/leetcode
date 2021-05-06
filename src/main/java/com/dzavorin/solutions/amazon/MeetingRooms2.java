package com.dzavorin.solutions.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRooms2 {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.<int[], Integer>comparing(interval -> interval[0])
                .thenComparing(interval -> interval[1]));
        int res = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] interval : intervals) {
            while (!pq.isEmpty() && pq.peek() <= interval[0]) {
                pq.poll();
            }
            pq.add(interval[1]);
            res = Math.max(pq.size(), res);
        }
        return res;
    }

    ///

    public int minMeetingRooms2(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(end);
        Arrays.sort(start);

        int startPointer = 0;
        int endPointer = 0;
        int usedRooms = 0;

        while (startPointer < intervals.length) {
            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                usedRooms--;
                endPointer++;
            }
            usedRooms++;
            startPointer++;

        }

        return usedRooms;
    }
}
