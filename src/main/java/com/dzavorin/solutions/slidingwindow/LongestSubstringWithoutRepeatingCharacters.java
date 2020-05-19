package com.dzavorin.solutions.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {

        if (s.equals("")) {
            return 0;
        }

        int lo = 0;
        int hi = 0;
        int res = 1;
        int count = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (hi < s.length()) {
            char ch = s.charAt(hi);
            if (!map.containsKey(ch)) {
                map.put(ch, hi);
                res = Math.max(res, hi + 1 - lo);
            } else {
                int idx = map.get(ch);
                if (idx < lo) {
                    map.put(ch, hi);
                    res = Math.max(res, hi + 1 - lo);
                } else {
                    lo = idx;
                    while (lo < hi && s.charAt(lo) == ch) {
                        lo++;
                    }
                    map.put(ch, hi);
                }
            }
            hi++;
        }

        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int lo = 0, hi = 0, counter = 0, d = 0;

        while (hi < s.length()) {
            // > 0 means repeating character
            //if(map[s.charAt(hi++)]-- > 0) counter++;
            char c = s.charAt(hi);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) counter++;
            hi++;

            while (counter > 0) {
                //if (map[s.charAt(lo++)]-- > 1) counter--;
                char charTemp = s.charAt(lo);
                if (map.get(charTemp) > 1) counter--;
                map.put(charTemp, map.get(charTemp) - 1);
                lo++;
            }
            d = Math.max(d, hi - lo);
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbb"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("ab"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("dvdf"));
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("tmmzuxt"));
    }
}
