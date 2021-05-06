package com.dzavorin.solutions.amazon;

import java.util.*;

public class PrisonCellsAfterNDays {

    public int[] prisonAfterNDays(int[] cells, int n) {
        Set<String> seen = new HashSet<>();
        boolean cycle = false;
        int len = 0;
        for (int i = 0; i < n; i++) {
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

        if (cycle) {
            return prisonAfterNDays(cells, n % len);
        }
        return cells;
    }

    private int[] nextDayState(int[] cells) {
        int[] next = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++)
            next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        return next;
    }

    ///


    int buildBitMask(int[] cells) {
        int bitMask = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == 1) {
                bitMask += (1 << i);
            }
        }
        return bitMask;
    }
    int setBit(int bitMask, int i, int value) {
        if ((bitMask & (1 << i)) > 0 && value == 0) {
            bitMask -= (1 << i);
        } else if ((bitMask & (1 << i)) == 0 && value == 1) {
            bitMask += (1 << i);
        }
        return bitMask;
    }
    int getBit(int bitMask, int i) {
        return (bitMask & (1 << i)) >> i;
    }
    int[] toArray(int bitMask) {
        int[] result = new int[8];
        for (int i = 0; i < 8; i++) {
            if ((bitMask & (1 << i)) > 0) {
                result[i] = 1;
            }
        }
        return result;
    }
    public int[] prisonAfterNDays2(int[] cells, int N) {
        int c = 0;

        // Array to bitmask
        for (int i = 0; i < cells.length; i++) {
            c += (cells[i] << i);
        }

        // Bitmask to Day map
        Map<Integer, Integer> m = new HashMap<>();

        while (N > 0) {
            // Store at which day we seen bitmask
            m.put(c, N);
            N--;

            // Each value is inversed XOR of values to the left and to the right
            // ~XOR gives 1 if two bit values are equal
            c = ~((c << 1) ^ (c >> 1));
            // Is a mask to make leftmost and rightmost bit 0
            // Leftmost and rightmost cells are always 0 as they don't have two neighbors
            c = c & 126;

            // Check if map already has the value
            // If the value is found we have a loop 'm.get(c) - N' days ago
            if (m.containsKey(c)) {
                // Calculate how many days left from the start of the loop
                // E.g. loop is 7 days and N == 51, we can skip 49 days
                N %= (m.get(c) - N);
            }
        }

        // Bitmask to array
        for (int i = 0; i < cells.length; i++) {
            cells[i] = (c >> i) & 1;
        }

        return cells;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new PrisonCellsAfterNDays().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7)));
        System.out.println(Arrays.toString(new PrisonCellsAfterNDays().prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000)));
    }
}
