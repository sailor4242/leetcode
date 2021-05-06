package com.dzavorin.solutions.amazon;

import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] dp = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || dp[i - 1]) {
                for (String word : wordDict) {
                    if (s.charAt(i) == word.charAt(0)
                            && i + word.length() <= s.length()) {
                        String substr = s.substring(i, i + word.length());
                        if (substr.equals(word)) {
                            dp[i + word.length() - 1] = true;
                        }
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    ///

    public boolean wordBreakBFS(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start]) {
                continue;
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDictSet.contains(s.substring(start, end))) {
                    queue.add(end);
                    if (end == s.length()) {
                        return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }

}
