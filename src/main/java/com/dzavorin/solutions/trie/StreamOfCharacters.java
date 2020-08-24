package com.dzavorin.solutions.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamOfCharacters {

    Trie trie = new Trie();
    List<TrieNode> prefixes = new ArrayList<>();
    int max = 0;

    public StreamOfCharacters(String[] words) {
        for (String word : words) {
            trie.add(word);
            max = Math.max(max, word.length());
        }
    }

    public boolean query(char letter) {
        int k = 0;
        List<TrieNode> nextPrefixes = new ArrayList<>();

        if (trie.root.map.containsKey(letter)) {
            TrieNode tr = trie.root.map.get(letter);
            if (tr.isWord) {
                k++;
            }
            nextPrefixes.add(trie.root.map.get(letter));
        }

        for (TrieNode p : prefixes) {
            if (p.map.containsKey(letter)) {
                TrieNode t = p.map.get(letter);
                if (t.isWord) k++;
                if (t.length < max) {
                    nextPrefixes.add(t);
                }
            }
        }
        prefixes = nextPrefixes;
        return k > 0;
    }


    static class Trie {

        TrieNode root = new TrieNode(0);

        void add(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                Character ch = word.charAt(i);
                if (!node.map.containsKey(ch)) {
                    node.map.put(ch, new TrieNode(node.length + 1));
                }

                if (i == word.length() - 1) {
                    node.map.get(ch).isWord = true;
                    return;
                }
                node = node.map.get(ch);
            }

        }

    }

    static class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
        boolean isWord = false;
        int length;

        TrieNode(int length) {
            this.length = length;
        }
    }

    static class StreamChecker {
        static class TreeNode {
            public boolean end = false;
            public TreeNode[] letters;

            public TreeNode() {
                this.letters = new TreeNode[26];
            }
        }

        private TreeNode root;
        private StringBuilder sb;

        public StreamChecker(String[] words) {
            root = new TreeNode();
            sb = new StringBuilder();

            for (var word : words) {
                insert(word);
            }
        }

        public void insert(String word) {
            TreeNode currNode = root;
            word = new StringBuilder(word).reverse().toString(); //insert from the back

            for (char c : word.toCharArray()) {
                if (currNode.letters[c - 'a'] == null) {
                    currNode.letters[c - 'a'] = new TreeNode();
                }
                currNode = currNode.letters[c - 'a'];
            }
            currNode.end = true;
        }

        public boolean query(char letter) {
            sb.append(letter);
            TreeNode currNode = root;

            for (int i = sb.length() - 1; i >= 0; i--) {
                if (currNode == null)
                    break;

                char c = sb.charAt(i);
                currNode = currNode.letters[c - 'a'];

                if (currNode != null && currNode.end)
                    return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        var st = new StreamOfCharacters(new String[]{"abaa", "abaab", "aabbb", "bab", "ab"});

        System.out.println(st.query('a'));
        System.out.println(st.query('a'));
        System.out.println(st.query('b'));
        System.out.println(st.query('b'));
        System.out.println(st.query('b'));
    }

}
