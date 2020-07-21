package com.dzavorin.solutions;

import java.util.LinkedList;

public class CourseSchedule2 {

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int len = prerequisites.length;
        int[] preCount = new int[numCourses];

        for (int i = 0; i < len; i++) {
            preCount[prerequisites[i][0]]++;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < preCount.length; i++) {
            if (preCount[i] == 0) {
                stack.add(i);
            }
        }

        int[] res = new int[numCourses];
        int count = 0;

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            res[count++] = cur;

            for (int i = 0; i < len; i++) {
                if (prerequisites[i][1] == cur) {
                    preCount[prerequisites[i][0]]--;
                    if (preCount[prerequisites[i][0]] == 0) {
                        stack.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return count == numCourses ? res : new int[]{};

    }
}
