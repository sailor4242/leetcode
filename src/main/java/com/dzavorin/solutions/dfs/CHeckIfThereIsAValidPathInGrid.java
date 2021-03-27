package com.dzavorin.solutions.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class CHeckIfThereIsAValidPathInGrid {

    Map<Integer, int[]> map = new HashMap<>();
    Map<Integer, Set<Integer>> rules = new HashMap<>();

    public CHeckIfThereIsAValidPathInGrid() {
        map.put(1, new int []{0,1});
        map.put(2, new int []{1,0});
        map.put(3, new int []{1,0});
        map.put(4, new int []{0,1});
        map.put(5, new int []{-1,0});
        map.put(6, new int []{0,1});
        map.put(-1, new int []{0,-1});
        map.put(-2, new int []{-1,0});
        map.put(-3, new int []{0,-1});
        map.put(-4, new int []{1,0});
        map.put(-5, new int []{0,-1});
        map.put(-6, new int []{-1,0});

        rules.put(1, Set.of(1,3,5));
        rules.put(2, Set.of(2,-5,6));
        rules.put(3, Set.of(2,-5,6));
        rules.put(4, Set.of(1,3,5));
        rules.put(5, Set.of(-2,-3,4));
        rules.put(6, Set.of(1,3,5));
        rules.put(-1, Set.of(-1,-4,-6));
        rules.put(-2, Set.of(-2,-3, 4));
        rules.put(-3, Set.of(-1,-6,-4));
        rules.put(-4, Set.of(6,-5,2));
        rules.put(-5, Set.of(-4,-1,-6));
        rules.put(-6, Set.of(4,-2,-3));
    }

    public boolean hasValidPath(int[][] grid) {
        if (grid.length == 0) return false;
        if (grid.length == 1 && grid[0].length == 1) return true;

        if (grid[0][0] == 1 || grid[0][0] == 2) {
            return bfs(grid, 1);
        } else {
            return bfs(grid, 1) || bfs(grid, -1);
        }
    }

    private boolean bfs(int[][] grid, int direction) {
        int n = grid.length - 1;
        int m = grid[0].length - 1;
        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{0,0, direction});
        boolean[][] visited = new boolean[n + 1][m + 1];

        while(!list.isEmpty()) {

            int[] cur = list.removeLast();
            if (cur[0] == n && cur[1] == m) return true;

            int curDir = cur[2];
            int street = grid[cur[0]][cur[1]];

            int[] nextDif = map.get(street * curDir);
            int i = cur[0] + nextDif[0];
            int j = cur[1] + nextDif[1];
            if (i < 0 || j < 0 || i > n || j > m) return false;
            int next = grid[i][j];

            if (!visited[i][j]) {
                visited[i][j] = true;
                if (rules.get(street * curDir).contains(next)) {
                    list.add(new int[]{i, j, 1});
                } else if (rules.get(street * curDir).contains(next * -1)) {
                    list.add(new int[]{i, j, -1});
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new CHeckIfThereIsAValidPathInGrid().hasValidPath(new int[][]{{4,1},{6,1}}));
    }
}
