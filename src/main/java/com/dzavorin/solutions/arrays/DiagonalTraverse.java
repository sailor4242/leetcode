package com.dzavorin.solutions.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {

        if (matrix.length == 0) return new int[0];

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                map.putIfAbsent(i + j, new ArrayList<>());
                map.get(i + j).add(matrix[i][j]);

            }
        }

        int[] res = new int[matrix.length * matrix[0].length];
        int r = 0;
        boolean direction = false;
        for (int i = 0; i <= (matrix.length - 1) + (matrix[0].length - 1); i++) {
            List<Integer> list = map.get(i);
            if (direction) {
                for (int j = 0; j < list.size(); j++) {
                    res[r++] = list.get(j);
                }
            } else {
                for (int j = list.size() - 1; j >= 0; j--) {
                    res[r++] = list.get(j);
                }
            }
            direction = !direction;
        }

        return res;
    }

}
