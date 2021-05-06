package com.dzavorin.solutions.monotonicqueue;

import java.util.LinkedList;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        int max = 0;

        int[] arr = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == '1' ) {
                    arr[j]++;
                } else {
                    arr[j] = 0;
                }

            }

            max = Math.max(max, maxHistogrm(arr));
        }

        return max;
    }

    private int maxHistogrm(int[] arr) { // 2, 0, 2, 1, 1
        int max = 0;
        MQ mq = new MQ();

        for (int i = 0; i <= arr.length; i++) {
            max = Math.max(max, mq.add(i < arr.length ? arr[i] : 0, i));
        }

        return max;
    }

    static class MQ {
        LinkedList<int[]> list = new LinkedList<>();

        public int add(int val, int i) {
            int max = 0;
            while (!list.isEmpty() && val < list.getLast()[0]) {
                int[] cur = list.removeLast();
                int width = i - 1 - (!list.isEmpty() ? list.getLast()[1] : -1);
                int height = cur[0];
                max = Math.max(max, width * height);
            }
            list.add(new int[]{val, i});
            return max;
        }
    }

    public static void main(String[] args) {
        System.out.println(new MaximalRectangle().maximalRectangle(
                new char[][]{
                        {'1', '0', '1', '0', '0'},
                        {'1', '0', '1', '1', '1'},
                        {'1', '1', '1', '1', '1'},
                        {'1', '0', '0', '1', '0'},
                }
        ));
    }
}
