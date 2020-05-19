package com.dzavorin.solutions;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || arr[i - 1] == 1) {
                for (String value : wordDict) {
                    if (s.charAt(i) == value.charAt(0) && i + value.length() <= s.length()) {
                        String temp = s.substring(i, i + value.length());
                        if (temp.equals(value)) {
                            arr[i + value.length() - 1] = 1;
                        }
                    }
                }
            }
        }
        return arr[arr.length - 1] == 1;
    }

    boolean res = false;
    boolean stop = false;

    public boolean wordBreak2(String s, List<String> wordDict) {
        Map<Character, Set<String>> map = new HashMap<>();
        Set<Character> chars = new HashSet<>();


        for (String w : wordDict) {

            for (Character c : w.toCharArray()) {
                chars.add(c);
            }

            map.compute(w.charAt(0), (k, v) -> {
                if (v == null) {
                    Set<String> set = new HashSet<>();
                    set.add(w);
                    return set;
                } else {
                    v.add(w);
                    return v;
                }
            });
        }

        char[] ch = s.toCharArray();

        for (Character c : ch) {
            if (!chars.contains(c)) {
                return false;
            }
        }


        go(ch, map, 0);
        return res;
    }

    public void go(char[] ch, Map<Character, Set<String>> map, int k) {
        if (stop) {
            return;
        }
        if (res) {
            return;
        }
        if (k == ch.length) {
            res = true;
        } else if (k < ch.length) {
            Set<String> set = map.get(ch[k]);
            if (set != null) {
                List<String> list = set.stream()
                        .filter(s -> k + s.length() <= ch.length
                                && s.equals(new String(Arrays.copyOfRange(ch, k, k + s.length()))))
                        .sorted(Comparator.comparing(String::length).reversed())
                        .collect(toList());
                if (list.isEmpty()) {
                    stop = true;
                }
                list.forEach(s -> go(ch, map, k + s.length()));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new WordBreak().wordBreak("leetcode", List.of("leet", "code")));
        System.out.println(new WordBreak().wordBreak("a", List.of("a")));
        System.out.println(new WordBreak().wordBreak("aaaaaaa", List.of("aaaa", "aa")));
        System.out.println(new WordBreak().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                List.of("aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa", "ba")));
    }

}
