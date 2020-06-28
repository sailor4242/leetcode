package com.dzavorin.solutions.dfs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaximumStudentTakingExam {

    // transform seats[][] array to a string so its easier to store state snapshots in a map
    // state snapshot is an entire array concatenated as a string
    // dfs on each '.' for two ways, either take a sit (+ 1 and mark related seats as not allowed with '#')
    // or dont take a sit and move to the next one

    int n;
    int m;
    Map<String, Integer> memo;
    char[][] seats;

    public int maxStudents(char[][] s) {
        memo = new HashMap<>();
        seats = s;
        n = seats.length;
        m = seats[0].length;

        // build seats[][] as a string
        String state = "";
        for (int i = 0; i < n; i++) {
            state += new String(s[i]);
        }

        return dfs(0, state.toCharArray());
    }

    public int dfs(int p, char[] state) {
        int max = 0;
        String stateStr = new String(state);

        // check if result for a state is already calculated
        Integer res = memo.get(stateStr);
        if (res != null) return res;

        while (p < n * m) {

            if (state[p] == '.') {

                int i = p / m;
                int j = p % m;

                // dfs for not taking a sit
                state[p] = '#';
                max = Math.max(max, dfs(p + 1, Arrays.copyOf(state, state.length)));

                // dfs for taking a sit, marking relative sits as not allowed with '#'
                if (j + 1 < m) {
                    state[i * m + j + 1] = '#';
                    if (i + 1 < n) {
                        state[(i + 1) * m + j + 1] = '#';
                    }
                }
                if (j - 1 >= 0) {
                    state[i * m + j - 1] = '#';
                    if (i + 1 < n) {
                        state[(i + 1) * m + j - 1] = '#';
                    }
                }
                max = Math.max(max, 1 + dfs(p + 1, Arrays.copyOf(state, state.length)));
            }

            p++;
        }

        memo.put(stateStr, max);

        return max;
    }

}
