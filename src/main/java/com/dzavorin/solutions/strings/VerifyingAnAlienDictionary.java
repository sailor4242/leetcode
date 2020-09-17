package com.dzavorin.solutions.strings;

import java.util.HashMap;
import java.util.Map;

public class VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {

        Map<Character, Integer> dict = new HashMap<>();

        for (int i = 0; i < order.length(); i++) {
            dict.put(order.charAt(i), i);
        }

        int[] prev = new int[words[0].length()];

        for (String word : words) {
            int[] next = new int[word.length()];
            boolean isLarger = false;
            for (int i = 0; i < word.length(); i++) {
                int weight = dict.get(word.charAt(i));
                if (isLarger) {
                    next[i] = weight;
                    continue;
                }

                if (i < prev.length) {
                    if (prev[i] > weight) {
                        return false;
                    } else if (prev[i] == weight) {
                        continue;
                    } else {
                        isLarger = true;
                    }
                } else if (i >= prev.length && !isLarger) {
                    isLarger = true;
                }

                next[i] = weight;
            }

            if (!isLarger && prev.length > next.length) return false;

            prev = next;
        }

        return true;
    }

    public boolean isAlienSorted3(String[] words, String order) {
        if (words.length <= 1) return true;
        int prev = 0, next = 0;
        for (int j = 1; j < words.length; j++) {
            int minLength = Math.min(words[j - 1].length(), words[j].length());
            for (int i = 0; i < minLength; i++) {
                prev = order.indexOf(words[j - 1].charAt(i));
                next = order.indexOf(words[j].charAt(i));
                if (prev != next) {
                    if (prev > next)
                        return false;
                    else if (j == words.length - 1)
                        return true;
                }
                if (i == minLength - 1 && words[j - 1].length() > words[j].length())
                    return false;
            }
        }
        return true;
    }

    public boolean isAlienSorted2(String[] words, String order) {

        for (int i = 0; i < words.length - 1; i++) {
            char[] chr1 = words[i].toCharArray();
            char[] chr2 = words[i + 1].toCharArray();
            int index = 0;
            for (int j = 0; j < order.length(); j++) {

                if (chr1[index] == chr2[index]) {
                    index++;
                    j--;
                    if (index >= chr1.length && index < chr2.length) {
                        break;
                    } else if (index < chr1.length && index >= chr2.length) {
                        return false;
                    } else if (index >= chr1.length && index >= chr2.length) {
                        break;
                    }
                    continue;
                }
                if (chr1[index] == order.charAt(j)) {
                    break;
                } else if (chr2[index] == order.charAt(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new VerifyingAnAlienDictionary().isAlienSorted(
                new String[]{"hello", "leetcode", "haaa"},
                "hlabcdefgijkmnopqrstuvwxyz"
        ));
    }
}
