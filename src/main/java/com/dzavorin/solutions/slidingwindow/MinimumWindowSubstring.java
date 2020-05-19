package com.dzavorin.solutions.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        String res = "";
        int resSize = Integer.MAX_VALUE;
        if (t.length() > s.length()) return res;

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i ++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }

        int lo = 0;
        int hi = 0;
        int counter = map.size();

        while (hi < s.length()) {
            char ch = s.charAt(hi);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) {
                    counter--;
                }
            }

            hi++;

            while (counter == 0) {
                char c = s.charAt(lo);

                if (map.containsKey(c)) {
                    if (map.get(c) == 0) counter++;
                    map.put(c, map.get(c) + 1);
                }

                if (resSize > hi - lo) {
                    resSize = hi-lo;
                    res = s.substring(lo, hi);
                }

                lo++;

            }
        }

        return res;

    }

}
