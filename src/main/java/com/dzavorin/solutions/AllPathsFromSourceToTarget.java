package com.dzavorin.solutions;

import java.util.*;

public class AllPathsFromSourceToTarget {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        int n = graph.length - 1;

        Map<Integer, List<LinkedList<Integer>>> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(n);
        List<LinkedList<Integer>> l = new ArrayList<>();
        l.add(list);
        map.put(n, l);

        go(map, graph);

        return res;
    }


    private void go(Map<Integer, List<LinkedList<Integer>>> map, int[][] graph) {

        Map<Integer, List<LinkedList<Integer>>> nextMap = new HashMap<>();

        for (Map.Entry<Integer, List<LinkedList<Integer>>> entry : map.entrySet()) {
            for (int i = 0; i < graph.length; i++) {
                for (int n : graph[i]) {
                    if (n == entry.getKey()) {
                        for (LinkedList<Integer> list : entry.getValue()) {
                            LinkedList<Integer> nextList = new LinkedList<>(list);
                            nextList.addFirst(i);
                            if (i == 0) {
                                res.add(nextList);
                                continue;
                            }
                            nextMap.putIfAbsent(i, new ArrayList<>());
                            nextMap.get(i).add(nextList);
                        }
                    }
                }
            }
        }

        if (!nextMap.isEmpty()) {
            go(nextMap, graph);
        }

    }

    // dfs -------

    private void dfs(int[][] graph, int source, List<Integer> list, List<List<Integer>> ans) {
        list.add(source);
        if (source == graph.length - 1) {
            ans.add(list);
        }

        for (int target : graph[source]) {
            List<Integer> temp = new ArrayList<>(list);
            dfs(graph, target, temp, ans);
        }
    }

    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(graph, 0, new ArrayList<>(), ans);
        return ans;
    }


    // bfs ------

    public List<List<Integer>> allPathsSourceTarget3(int[][] graph) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        //This queue will store all the paths
        Queue<List<Integer>> paths = new LinkedList();
        paths.add(Arrays.asList(0));

        int goal = graph.length - 1;
        while (!paths.isEmpty()) {
            List<Integer> currPath = paths.poll();
            //Extracting the last node
            int lastNode = currPath.get(currPath.size() - 1);
            //if lastNode=destination, then we have reached the goal, add to result
            if (lastNode == goal)
                res.add(new ArrayList<Integer>(currPath));
            else {
                //We need to extract the neighbours from the current node
                int[] neighbour = graph[lastNode];
                //Add every possible path to the queue
                for (int n : neighbour) {
                    List<Integer> temp = new ArrayList<Integer>(currPath);
                    temp.add(n);
                    paths.add(temp);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new AllPathsFromSourceToTarget().allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
    }

}
