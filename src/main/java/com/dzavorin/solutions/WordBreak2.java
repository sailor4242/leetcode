package com.dzavorin.solutions;

import java.util.*;

public class WordBreak2 {

    Map<String, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict));
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> list = new ArrayList<>();
        if (wordDict.contains(s)) {
            list.add(s);
        }
        for (String word : wordDict) {
            int diff = s.length() - word.length();
            if (diff > 0 && word.equals(s.substring(diff))) {
                List<String> prior = wordBreak(s.substring(0, diff), wordDict);
                for (String s1 : prior) {
                    list.add(s1 + " " + word);
                }

            }
        }

        map.put(s, list);
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new WordBreak2().wordBreak("catsanddog", List.of("cat", "cats", "and", "sand", "dog")));
        System.out.println(new WordBreak2().wordBreak("abcd", List.of("a", "abc", "b", "cd")));
        System.out.println(new WordBreak2().wordBreak("goalspecial", List.of("go", "goal", "goals", "special")));
    }
}
