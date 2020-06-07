package com.dzavorin.solutions;

import java.util.*;

public class QueueReconstructionByHeight {

    public int[][] reconstructQueue(int[][] people) {
        int[][] arr = new int[people.length][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0]));
        for (int i = 0; i < people.length; i++) {
            pq.add(people[i]);
        }
        Arrays.fill(arr, new int[]{-1,-1});
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            int k = p[1] + 1;
            int i = 0;
            while (k != 0) {
                if (Arrays.equals(arr[i], new int[]{-1,-1}) || arr[i][0] >= p[0]) {
                    k--;
                }
                i++;
            }
            arr[i - 1] = p;
        }

        return arr;
    }

    public int[][] reconstructQueue2(int[][] people) {
        // sort by heights in decreasing order
        // if same height, then increasing order of k (people ahead)
        Arrays.sort(people, (x, y) -> x[0] == y[0] ? x[1] - y[1] : y[0] - x[0]);

        List<int[]> list = new ArrayList<int[]>();
        int i, N = people.length, position;
        for (i = 0; i < N; i++) {
            // keep people[i] at index people[i][1]
            position = people[i][1];
            // list add shifts existing element to the right if its there !!
            list.add(position, people[i]);
        }

        return list.toArray(new int[N][2]);
    }

}
