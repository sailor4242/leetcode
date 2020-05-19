package com.dzavorin.solutions.slidingwindow;

import java.util.*;

public class SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String S, String[] words) {
        List<Integer> res = new LinkedList<>();

        if (words.length == 0 || S.length() < words.length * words[0].length()) return res;

        int n = S.length();
        int m = words.length; // *** length
        int wl = words[0].length();

        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> curMap = new HashMap<>();
        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        String str = null, tmp = null;

        for (int i = 0; i < wl; i++) {
            int count = 0;  // remark: reset count
            int start = i;
            for (int r = i; r + wl <= n; r += wl) {
                str = S.substring(r, r + wl);
                if (map.containsKey(str)) {

                    curMap.put(str, curMap.getOrDefault(str, 0) + 1);

                    if (curMap.get(str) <= map.get(str)) {
                        count++;
                    }
                    while (curMap.get(str) > map.get(str)) {
                        tmp = S.substring(start, start + wl);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        start += wl;

                        if (curMap.get(tmp) < map.get(tmp)) {
                            count--;
                        }

                    }
                    if (count == m) {
                        res.add(start);
                        tmp = S.substring(start, start + wl);
                        curMap.put(tmp, curMap.get(tmp) - 1);
                        start += wl;
                        count--;
                    }
                } else {
                    curMap.clear();
                    count = 0;
                    start = r + wl;
                }
            }
            curMap.clear();
        }
        return res;
    }

    public List<Integer> findSubstring2(String s, String[] words) {
        final Map<String, Integer> counts = new HashMap<>();
        for (final String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        final List<Integer> indexes = new ArrayList<>();
        final int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i < n - num * len + 1; i++) {
            final Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                final String word = s.substring(i + j * len, i + (j + 1) * len);
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == num) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static void main(String[] args) {
        System.out.println(new SubstringWithConcatenationOfAllWords()
                .findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));
    }
}
