package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindLatestGroupOfSizeM {

    public int findLatestStep(int[] arr, int m) {
        Map<Integer, Integer> forw = new HashMap<>();
        Map<Integer, Integer> back = new HashMap<>();

        Set<Integer> matched = new HashSet<>();

        int lastStep = -1;

        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            int b;
            int f;

            if (forw.containsKey(n + 1) && back.containsKey(n - 1)) {
                b = forw.get(n + 1);
                f = back.get(n - 1);

                matched.remove(f);
                matched.remove(n + 1);

                forw.remove(n + 1);
                back.remove(n - 1);
            } else if (forw.containsKey(n + 1)) {
                b = forw.get(n + 1);
                f = n;

                forw.remove(n + 1);
                back.remove(b);

                matched.remove(n + 1);
            } else if (back.containsKey(n - 1)) {
                f = back.get(n - 1);
                b = n;
                back.remove(n - 1);
                forw.remove(f);

                matched.remove(f);
            } else {
                f = n;
                b = n;
            }

            back.put(b, f);
            forw.put(f, b);
            int dif = b - f + 1;

            if (dif == m) {
                matched.add(f);
            }

            if (!matched.isEmpty()) {
                lastStep = i + 1;
            }
        }

        return lastStep;
    }

    int findLatestStep2(int[] arr, int m) {
        int res = -1;
        int n = arr.length;
        if (n == m) {
            return m;
        }
        int[] intervals = new int[n + 2];
        for (int i = 0; i < n; i++) {
            int a = arr[i];
            int left = intervals[a - 1];
            int right = intervals[a + 1];
            if (left == m || right == m) {
                res = i;
            }
            intervals[a - left] = intervals[a + right] = left + right + 1;
        }
        return res;
    }

}
