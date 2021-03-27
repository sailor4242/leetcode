package com.dzavorin.solutions.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ShortEncodingOfWords {

    public int minimumLengthEncoding(String[] words) {
        Set<String> set = Arrays.stream(words).collect(Collectors.toSet());
        for (String word : words) {
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i));
            }
        }

        int res = 0;
        for (String word : set) {
            res += word.length() + 1;
        }

        return res;
    }

    static class Solution {

        public int minimumLengthEncoding(String[] words) {
            TrieNode root = new TrieNode();
            Map<TrieNode, Integer> nodes = new HashMap<>();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                TrieNode cur = root;
                for (int j = word.length() - 1; j >= 0; j--) {
                    cur = cur.get(word.charAt(j));
                }

                nodes.put(cur, i);
            }

            int ans = 0;
            for (TrieNode node : nodes.keySet()) {
                if (node.count == 0)
                    ans += words[nodes.get(node)].length() + 1;
            }
            return ans;

        }
    }

    static class TrieNode {
        TrieNode[] children;
        int count;

        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }

        public TrieNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new TrieNode();
                count++;
            }
            return children[c - 'a'];
        }
    }

}
