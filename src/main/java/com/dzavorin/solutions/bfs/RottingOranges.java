package com.dzavorin.solutions.bfs;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree;

import java.util.HashSet;
import java.util.Set;

import static com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.*;

public class RottingOranges {

    int[] directioons = new int[]{0, 1, 0, -1, 0};

    public int orangesRotting(int[][] grid) {

        Set<Pair<Integer, Integer>> set = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    for (int d = 0; d < directioons.length - 1; d++) {
                        int id = i + directioons[d];
                        int jd = j + directioons[d + 1];
                        if (id >= 0 && id < grid.length
                                && jd >= 0 && jd < grid[0].length
                                && grid[id][jd] == 1) {

                            set.add(new Pair<>(id, jd));
                        }
                    }
                }
            }
        }

        for (Pair<Integer, Integer> pair : set) {
            grid[pair.getKey()][pair.getValue()] = 2;
        }

        int count = 0;

        while (!set.isEmpty()) {
            Set<Pair<Integer, Integer>> nextSet = new HashSet<>();

            for (Pair<Integer, Integer> pair : set) {
                for (int d = 0; d < directioons.length - 1; d++) {
                    int id = pair.getKey() + directioons[d];
                    int jd = pair.getValue() + directioons[d + 1];
                    if (id >= 0 && id < grid.length
                            && jd >= 0 && jd < grid[0].length
                            && grid[id][jd] == 1) {

                        nextSet.add(new Pair<>(id, jd));
                        grid[id][jd] = 2;
                    }
                }
            }

            set = nextSet;
            count++;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return count;
    }

}
