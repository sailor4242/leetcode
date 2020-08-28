package com.dzavorin.solutions.dynamic;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree;
import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
When user goes forward, collects maximum cherries, there is a possibility that the return path may not cover a total of maximum cherries. We may hve to find a way to optimize the COMBINED paths of forward and backward.

This can be done by moving forward together as 2 users from beginning to end.
Since the users travel together, they will technically reach the end point together.

Caveat : return 1 user's exit and not both's ( thats not even possible )
> When they are in the same point, cover just 1 cherry, meaning the other won't get it.
*/
public class CherryPickup {
    /// O(N^3)

    Map<String, Integer> map;

    public int cherryPickup(int[][] grid) {
        map = new HashMap<>();
        return Math.max(0, travelForward(0, 0, 0, 0, grid));
    }

    /**
     * The person travels as 2 people forward.
     * There are 4 combinations for this :
     * i + 1, j, i1 + 1, j1 -> both go down
     * i + 1, j, i1, j1 + 1 -> first goes down, second goes to the right
     * i, j + 1, i1 + 1, j1 -> first goes to the right, second goes down
     * i, j + 1, i1, j1 + 1 -> first goes to the right, second goes to the right
     * <p>
     * We have to obtain the max of this
     */
    private int travelForward(int i, int j, int i1, int j1, int[][] grid) {
        if (i > grid.length - 1 || j > grid[0].length - 1 || i1 > grid.length - 1 || j1 > grid[0].length - 1 || grid[i][j] == -1 || grid[i1][j1] == -1)
            return Integer.MIN_VALUE;

        String key = i + "-" + j + "-" + i1 + "-" + j1;
        if (map.containsKey(key)) return map.get(key);

        if (i == grid.length - 1 && j == grid[0].length - 1) return grid[i][j];
        if (i1 == grid.length - 1 && j1 == grid[0].length - 1) return grid[i1][j1];

        int res = (i == i1 && j == j1) ? grid[i][j] : grid[i][j] + grid[i1][j1]; // Combine

        res += Math.max(
                Math.max(travelForward(i + 1, j, i1 + 1, j1, grid),
                        travelForward(i + 1, j, i1, j1 + 1, grid)
                ),
                Math.max(travelForward(i, j + 1, i1 + 1, j1, grid),
                        travelForward(i, j + 1, i1, j1 + 1, grid)
                )
        );

        map.put(key, res);
        return res;
    }

    ///// O(N^4)

    public int cherryPickup2(int[][] grid) {
        int n = grid.length;
        return Math.max(0, dfs(grid, n, 0, 0, 0, 0, new Integer[n][n][n][n]));
    }

    private int dfs(int[][] grid, int n, int r1, int c1, int r2, int c2, Integer memo[][][][]) {
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1)
            return Integer.MIN_VALUE;
        if (r1 == n - 1 && c1 == n - 1) return grid[r1][c1];
        if (memo[r1][c1][r2][c2] != null) return memo[r1][c1][r2][c2];

        int cherries = Math.max(
                Math.max(dfs(grid, n, r1 + 1, c1, r2 + 1, c2, memo),
                        dfs(grid, n, r1 + 1, c1, r2, c2 + 1, memo)),
                Math.max(dfs(grid, n, r1, c1 + 1, r2 + 1, c2, memo),
                        dfs(grid, n, r1, c1 + 1, r2, c2 + 1, memo)));

        return memo[r1][c1][r2][c2] = cherries + (r1 == r2 && c1 == c2 ? grid[r1][c1] : grid[r1][c1] + grid[r2][c2]);
    }


    public static void main(String[] args) {
        System.out.println(new CherryPickup().cherryPickup(new int[][]{
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}}));

        System.out.println(new CherryPickup().cherryPickup(new int[][]{
                {1, 1, -1},
                {1, -1, 1},
                {-1, 1, 1}}));

        System.out.println(new CherryPickup().cherryPickup(new int[][]{
                {1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1}
        }));
    }
}
