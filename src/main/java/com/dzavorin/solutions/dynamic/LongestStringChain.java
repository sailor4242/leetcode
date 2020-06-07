package com.dzavorin.solutions.dynamic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain {

    public int longestStrChain(String[] words) {

        Arrays.sort(words, Comparator.comparing(String::length));
        int[] dp = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (words[i].length() > words[j].length() && isPredecessor(words[i], words[j])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

        }

        return Arrays.stream(dp).max().getAsInt();
    }

    public boolean isPredecessor(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) != 1) {
            return false;
        }

        boolean jumped = false;

        int i = 0;
        int j = 0;
        String shorter;
        String longer;

        if (s1.length() < s2.length()) {
            shorter = s1;
            longer = s2;
        } else {
            shorter = s2;
            longer = s1;
        }

        while (i < shorter.length() && j < longer.length()) {

            if (shorter.charAt(i) != longer.charAt(j)) {

                if (jumped) {
                    return false;
                }

                jumped = true;
                j++;

            } else {
                i++;
                j++;
            }

        }

        return true;
    }

    public int longestStrChain2(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length));

        Map<String, Integer> dp = new HashMap<>();

        int maxLength = 1;
        for (String cur : words) {
            dp.put(cur, 1);
            for (int i = 0; i < cur.length(); i++) {
                String pre = cur.substring(0, i) + cur.substring(i + 1);
                if (dp.containsKey(pre) && dp.get(pre) + 1 > dp.get(cur)) {
                    dp.put(cur, dp.get(pre) + 1);
                    maxLength = Math.max(maxLength, dp.get(cur));
                }
            }
        }

        return maxLength;
    }
}
