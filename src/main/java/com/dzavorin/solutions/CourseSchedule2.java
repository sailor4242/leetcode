package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            list.add(new ArrayList<>());
        }
        int[] degree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int to = prerequisites[i][0];
            int from = prerequisites[i][1];
            degree[to]++;
            list.get(from).add(to);
        }
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.add(i);
                res.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> toList = list.get(node);
            for (int to : toList) {
                degree[to]--;
                if (degree[to] == 0) {
                    queue.offer(to);
                    res.add(to);
                }
            }
        }
        if (res.size() < numCourses) {
            return new int[0];
        } else {
            int[] array = new int[numCourses];
            for (int i = 0; i < array.length; i++) {
                array[i] = res.get(i);
            }
            return array;
        }
    }
}
