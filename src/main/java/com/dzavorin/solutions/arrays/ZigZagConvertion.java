package com.dzavorin.solutions.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZigZagConvertion {

    public String convert(String s, int n) {

        int diff = n - 2;

        Map<Integer, StringBuilder> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(i, new StringBuilder());
        }

        boolean down = true;
        int i = 0;
        while (i < s.length()) {

            if (down) {
                for (int j = 0; j < n; j++) {
                    if (i + j < s.length()) {
                        map.get(j).append(s.charAt(i + j));
                    }
                }
                down = false;
                i += n;
            } else {
                if (diff <= 0) {
                    down = true;
                    continue;
                }
                for (int j = 0; j < diff; j++) {
                    if (i + j < s.length()) {
                        map.get(n - j - 2).append(s.charAt(i + j));
                    }
                }
                down = true;
                i += diff;
            }

        }


        StringBuilder res = new StringBuilder();

        for (int l = 0; l < n; l++) {
            System.out.println(map.get(l).toString());
            res.append(map.get(l).toString());
        }

        return res.toString();
    }


    ////

    public String convert2(String s, int n) {

        if (n == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();

        for (int i = 0; i < Math.min(n, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean down = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == n - 1) {
                down = !down;
            }
            curRow += down ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }

        return res.toString();
    }


    ////


    public String convert3(String s, int n) {

        if (n == 1) return s;

        StringBuilder res = new StringBuilder();

        int cycleLen = 2 * n - 2;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j + i < s.length(); j += cycleLen) {
                res.append(s.charAt(j + i));
                if (i != 0 && i != n - 1 && j + cycleLen - i < s.length())
                    res.append(s.charAt(j + cycleLen - i));
            }
        }
        return res.toString();
    }

}
