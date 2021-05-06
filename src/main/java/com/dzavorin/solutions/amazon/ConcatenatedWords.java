package com.dzavorin.solutions.amazon;

import java.util.*;

public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        HashSet<String> wordSet = new HashSet<>(Arrays.asList(words));
        HashMap<String, Boolean> cache = new HashMap<>();
        for (String word : words) if (dfs(word, wordSet, cache)) ans.add(word);
        return ans;
    }

    boolean dfs(String word, HashSet<String> wordSet, HashMap<String, Boolean> cache) {
        if (cache.containsKey(word)) return cache.get(word);
        for (int i = 1; i < word.length(); i++) {
            if (wordSet.contains(word.substring(0, i))) {
                String suffix = word.substring(i);
                if (wordSet.contains(suffix) || dfs(suffix, wordSet, cache)) {
                    cache.put(word, true);
                    return true;
                }
            }
        }
        cache.put(word, false);
        return false;
    }

    /// bottom-up

    public List<String> findAllConcatenatedWordsInADict3(String[] words) {
        List<String> result = new ArrayList<>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Set<String> preWords = new HashSet<>();
        for (int i = 0; i < words.length; i++) {     // O(n)
            if (isConcatenated(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }
        return result;
    }

    private boolean isConcatenated(String word, Set<String> set) {
        boolean[] dp = new boolean[word.length() + 1];
        for (int i = 1; i < word.length() + 1; i++) {
            if (set.contains(word.substring(0, i))) {
                dp[i] = true;
                continue;
            } else {
                for (int j = 1; j < i; j++) {
                    if (dp[j] && set.contains(word.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[word.length()];
    }
    ///// trie

    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }

        List<String> res = new LinkedList<>();
        for (String word : words) {
            if (trie.checkIfConcatenated(word, 0)) {
                res.add(word);
            }
        }
        return res;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        Trie() {
        }

        public void add(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (!cur.nodes.containsKey(ch)) {
                    cur.nodes.put(ch, new TrieNode());
                }
                cur = cur.nodes.get(ch);
            }
            cur.isWord = true;
        }

        public boolean checkIfConcatenated(String word, int j) {
            TrieNode cur = root;
            for (int i = j; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (cur.nodes.containsKey(ch)) {
                    cur = cur.nodes.get(ch);
                    if (cur.isWord && checkIfConcatenated(word, i + 1)) {
                        return true;
                    }
                } else {
                    return false;
                }
            }

            return j != 0 && cur.isWord;
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> nodes = new HashMap<>();
        boolean isWord = false;

        TrieNode() {
        }
    }

    public static void main(String[] args) {
        System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(
                new String[]{"catdog", "cat", "dog"}));
        System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(
                new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"}));
    }
}
