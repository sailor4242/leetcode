package com.dzavorin.solutions.strings;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {

    public int longestPalindrome(String s) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        boolean hasSingle = false;
        int count = 0;

        for (Integer n : map.values()) {
            if (n > 1) {
                if (n % 2 == 0) {
                    count += n;
                } else {
                    count += n - 1;
                    hasSingle = true;
                }
            } else if (!hasSingle) {
                hasSingle = true;
            }
        }

        return hasSingle ? count + 1 : count;

    }

}
