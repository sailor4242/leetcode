package com.dzavorin.solutions.amazon;

import java.util.*;

public class WordBreak2 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length()];
        Map<Integer, List<String>> subs = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || dp[i - 1]) {
                for (String word : dict) {
                    if (s.charAt(i) == word.charAt(0)
                            && i + word.length() <= s.length()) {
                        String substr = s.substring(i, i + word.length());
                        if (substr.equals(word)) {
                            int idx = i + word.length() - 1;
                            dp[idx] = true;
                            subs.putIfAbsent(idx, new ArrayList<>());

                            if (i == 0) {
                                subs.get(idx).add(substr);
                            } else {
                                List<String> curSubs = subs.get(i - 1);
                                List<String> newSubs = new ArrayList<>();
                                for (String sub : curSubs) {
                                    newSubs.add(sub + " " + substr);
                                }
                                subs.get(idx).addAll(newSubs);
                            }
                        }
                    }
                }
            }
        }

        return subs.get(s.length() - 1) == null ? List.of() : subs.get(s.length() - 1);
    }

}
