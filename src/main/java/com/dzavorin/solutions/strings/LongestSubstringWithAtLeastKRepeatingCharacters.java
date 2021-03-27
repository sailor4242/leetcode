package com.dzavorin.solutions.strings;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtLeastKRepeatingCharacters {

    // sliding window

    public int longestSubstring(String s, int k) {
        if (s.length() == 0) return 0;
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        int left = -1;
        int right = s.length();
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (arr[s.charAt(i) - 'a'] < k) {
                while (left != i) {
                    arr[s.charAt(++left) - 'a']--;
                }
                i++;
                j = right - 1;
            } else if (arr[s.charAt(j) - 'a'] < k) {
                while (right != j) {
                    arr[s.charAt(--right) - 'a']--;
                }
                j--;
                i = left + 1;
            } else {
                i++;
                j--;
            }

        }

        return right - left - 1;
    }

    // dfs
    public int longestSubstring2(String s, int k) {
        return fn(s, k);
    }

    public int fn(String s, int k) {
        if (s.length() < k) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        for (char key : map.keySet()) {
            if (map.get(key) < k) {
                int ans = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == key) {
                        ans = Math.max(ans, fn(s.substring(0, i), k));
                        s = s.substring(i + 1, s.length());
                        i = 0;
                    }
                }
                ans = Math.max(ans, fn(s, k));
                return ans;
            }
        }
        return s.length();
    }


    // divide and conqure
    public int longestSubstring3(String s, int k) {
        return div(s, 0, s.length(), k);
    }

    static int div(String s, int start, int end, int k) {
        if (end - start < k) {
            return 0;
        }

        int[] arr = new int[26];
        for (int i = start; i < end; i++) {
            arr[s.charAt(i) - 'a']++;
        }

        int max = 0;
        int i = start;
        for (int j = start; j < end; j++) {
            if (arr[s.charAt(j) - 'a'] < k) {
                max = Math.max(max, div(s, i, j, k));
                i = j + 1;
            } else if (j == end - 1) {
                if (i == start) {
                    return end - start;
                }
                max = Math.max(max, div(s, i, end, k));
            }
        }
        return max;
    }


    //same as above
    public int longestSubstring4(String s, int k) {
        return div4(s, 0, s.length(), k);
    }

    static int div4(String s, int start, int end, int k) {
        if (end - start < k) {
            return 0;
        }

        int[] arr = new int[26];
        for (int i = start; i < end; i++) {
            arr[s.charAt(i) - 'a']++;
        }

        int max = 0;
        boolean valid = true;
        for (int j = start; j < end; j++) {
            if (arr[s.charAt(j) - 'a'] < k) {
                max = Math.max(
                        Math.max(max, div(s, start, j, k)),
                        Math.max(max, div(s, j + 1, end, k))
                );
                valid = false;
                break;
            }
        }

        if (valid) {
            return end - start;
        } else {
            return max;
        }
    }


    public static void main(String[] args) {
//        System.out.println(new LongestSubstringWithAtLeastKRepeatingCharacters()
//                .longestSubstring("aaabb", 3));
//
//        System.out.println(new LongestSubstringWithAtLeastKRepeatingCharacters()
//                .longestSubstring("ababb", 2));

        System.out.println(new LongestSubstringWithAtLeastKRepeatingCharacters()
                .longestSubstring("bbaaacbd", 3));
    }
}
