package com.dzavorin.solutions.amazon;

import java.util.*;

public class CourseSchedule2 {

    // dfs
    boolean isPossible = true;
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pre : prerequisites) {
            graph.putIfAbsent(pre[1], new ArrayList<>());
            graph.get(pre[1]).add(pre[0]);
        }

        List<Integer> list = new ArrayList<>();
        int[] status = new int[numCourses];
        // 1-notVisited, 2-visited, 3-done
        for (int i = 0; i < numCourses; i++) {
            status[i] = 1;
        }

        for (int i = 0; i < numCourses; i++) {
            if (status[i] == 1) {
                dfs(i, list, graph, status);
            }
        }

        if (!isPossible) return new int[]{};

        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = list.get(numCourses - i - 1);
        }
        return res;
    }

    void dfs(int n, List<Integer> list, Map<Integer, List<Integer>> graph, int[] status) {
        if (!this.isPossible) return;

        status[n] = 2;

        for (Integer next: graph.getOrDefault(n, new ArrayList<>())) {
            if (status[next] == 1) {
                dfs(next, list, graph, status);
            } else if (status[next] == 2) {
                this.isPossible = false;
            }
        }

        status[n] = 3;
        list.add(n);
    }

    /// bfs indegree

    public int[] findOrder2(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph.putIfAbsent(pre[1], new ArrayList<>());
            graph.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++;
        }


        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                list.add(i);
            }
        }

        int j = 0;
        int[] res = new int[numCourses];
        while (!list.isEmpty()) {
            Integer cur = list.removeFirst();
            res[j++] = cur;
            for (Integer next : graph.getOrDefault(cur, List.of())) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    list.add(next);
                }
            }
        }

        if (j != numCourses) return new int[]{};

        return res;
    }

}
