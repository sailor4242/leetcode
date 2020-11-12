package com.dzavorin.solutions.math;

import java.util.HashMap;

public class ValidSquare {

    // Checking the number of unique lengths via map
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashMap<Long, Integer> map = new HashMap<>();
        int a[][] = {p1, p2, p3, p4};
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                int xl = a[i][0] - a[j][0];
                int yl = a[i][1] - a[j][1];
                long l = xl * xl + yl * yl;
                if (l == 0) return false;
                map.put(l, 1);
                if (map.size() > 2) return false;
            }
        }

        return true;
    }

}
