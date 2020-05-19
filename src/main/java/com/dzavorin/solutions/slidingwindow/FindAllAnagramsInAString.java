package com.dzavorin.solutions.slidingwindow;

import java.util.*;

public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (p.length() > s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) counter--;
            }
            end++;

            while (counter == 0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    if (map.get(tempc) == 0) {
                        counter++;
                    }
                    map.put(tempc, map.get(tempc) + 1);
                }
                if (end - begin == p.length()) {
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int[] ana = new int[26];

        for (int i = 0; i < p.length(); i++) {
            ana[p.charAt(i) - 97]++;
        }

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            int idx = s.charAt(i) - 97;
            if (idx < 26 && ana[idx] != 0) {
                int[] arr = Arrays.copyOf(ana, ana.length);
                for (int j = 0; j < p.length(); j++) {
                    arr[s.charAt(i + j) - 97]--;
                    if (arr[s.charAt(i + j) - 97] < 0) {
                        break;
                    }
                    if (j + 1 == p.length()) {
                        boolean good = true;
                        for (int value : arr) {
                            if (value != 0) {
                                good = false;
                                break;
                            }
                        }
                        if (good) {
                            res.add(i);
                        }
                    }
                }
            }

        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new FindAllAnagramsInAString().findAnagrams("cbaebabacd", "abc"));
        System.out.println(new FindAllAnagramsInAString().findAnagrams("acdcaeccde", "c"));
        System.out.println(new FindAllAnagramsInAString().findAnagrams("abab", "ab"));
    }
}
