package com.dzavorin.solutions.arrays;

import java.util.*;

public class PrisonCellsAfterNDays {

    public int[] prisonAfterNDays(int[] cells, int N) {
        N = (N - 1) % 14 + 1;
        for (int i = 0; i < N; i++)
            cells = nextDayState(cells);
        return cells;
    }

    private int[] nextDayState(int[] cells) {
        int[] next = new int[cells.length];
        for(int i = 1; i < cells.length - 1; i++)
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        return next;
    }

    public int[] prisonAfterNDays2(int[] cells, int N) {
        Set<String> seen = new HashSet();
        boolean cycle = false;
        int len = 0;
        for (int i = 0; i < N; i++) {
            int[] next = nextDayState(cells);
            String key = Arrays.toString(next);
            if (seen.contains(key)) {
                cycle = true;
                break;
            }
            seen.add(key);
            len++;
            cells = next;
        }

        if (cycle)
            return prisonAfterNDays2(cells, N % len);
        return cells;
    }

    private int[] nextDayState2(int[] cells) {
        int[] next = new int[cells.length];
        for (int i = 1; i < 7; i++)
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        return next;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PrisonCellsAfterNDays().prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000)));
    }

}
