package com.dzavorin.solutions.strings;

import java.util.*;

public class FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        String p = getPattern(pattern);
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (getPattern(word).equals(p)) {
                res.add(word);
            }
        }

        return res;
    }

    private String getPattern(String word) {
        LinkedHashMap<Character, List<Integer>> map = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            map.putIfAbsent(word.charAt(i), new ArrayList<>());
            map.get(word.charAt(i)).add(i);
        }

        map.forEach((ch, list) -> {
            for (Integer i : list) {
                sb.append(i);
            }
            sb.append("-");
        });

        return sb.toString();
    }

    ////

    public List<String> findAndReplacePattern2(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        int target = wordHash(pattern);
        for (String s : words) {
            if (target == wordHash(s)) {
                res.add(s);
            }
        }
        return res;
    }

    private int wordHash(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int hash = 0;
        int unique = 1;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (seen.containsKey(c)) {
                hash = (hash * 10) + seen.get(c);
            } else {
                seen.put(c, unique);
                hash = (hash * 10) + unique;
                unique++;
            }
        }
        return hash;
    }

    /////

    public static List<String> findAndReplacePattern3(String[] words, String pattern) {
        int[] pos1 = new int[26];
        Arrays.fill(pos1, -1);
        for (int i = 0; i < pattern.length(); i++) {
            char ch = pattern.charAt(i);
            if (pos1[ch - 'a'] == -1) {
                pos1[ch - 'a'] = i;
            }
        }
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (check(word, pattern, pos1)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private static boolean check(String word, String pattern, int[] pos1) {
        int[] pos2 = new int[26];
        Arrays.fill(pos2, -1);
        for (int i = 0; i < word.length(); i++) {
            char pch = pattern.charAt(i);
            char wch = word.charAt(i);
            int idx = pos1[pch - 'a'];
            if (idx == i) {
                if (pos2[wch - 'a'] != -1) {
                    return false;
                } else {
                    pos2[wch - 'a'] = idx;
                }
            } else if (word.charAt(idx) != wch) {
                return false;
            }
        }
        return true;
    }

}
