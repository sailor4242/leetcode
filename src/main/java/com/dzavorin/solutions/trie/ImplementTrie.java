package com.dzavorin.solutions.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class TrieNode {
    // Initialize your data structure here.
    public boolean isWord;
    public Map<Character, TrieNode> next; // HashMap
    public TrieNode() {
        isWord = false;
        next = new HashMap<>();
    }
}

public class ImplementTrie {
    private TrieNode root;

    public ImplementTrie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (Character c : word.toCharArray()) {
            if (!node.next.containsKey(c)) {
                node.next.put(c, new TrieNode());
            }
            node = node.next.get(c);
        }
        node.isWord = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = get(word);
        return node != null && node.isWord;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = get(prefix);
        return node != null;
    }

    //
    private TrieNode get(String key) {
        TrieNode node = root;
        for (Character c : key.toCharArray()) {
            node = node.next.get(c);
            if (node == null) return null;
        }
        return node;
    }

    static class Trie2 {

        Map<String, Boolean> trie;

        /** Initialize your data structure here. */
        public Trie2() {
            this.trie = new HashMap<>();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            for (int i = 0; i < word.length(); i++) {
                String s = word.substring(0, i + 1);
                Boolean b = trie.get(s);
                if (b == null || b == false) {
                    trie.put(s, i == word.length() - 1 ? true : false);
                }
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            return Optional.ofNullable(trie.get(word)).orElse(false);
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            return trie.get(prefix) != null;
        }

    }
}
