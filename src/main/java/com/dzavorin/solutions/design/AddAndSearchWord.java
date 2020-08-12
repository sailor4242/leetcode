package com.dzavorin.solutions.design;

import java.util.HashMap;
import java.util.Map;

public class AddAndSearchWord {

    Node root = new Node();

    /** Initialize your data structure here. */
    public AddAndSearchWord() {

    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        addWord(word, 0, root);
    }

    private void addWord(String word, int i, Node node) {
        if (i == word.length()) {
            node.isWord = true;
            return;
        }

        if (!node.next.containsKey(word.charAt(i))) {
            node.next.put(word.charAt(i), new Node());
        }

        addWord(word, i + 1, node.next.get(word.charAt(i)));
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int i, Node cur) {

        while (i < word.length()) {
            char c = word.charAt(i);
            if (c == '.') {
                final int j = i + 1;
                return cur.next.entrySet().stream().anyMatch(e -> search(word, j, e.getValue()));
            } else {
                if (cur.next.containsKey(c)) {
                    cur = cur.next.get(c);
                } else {
                    return false;
                }
            }
            i++;
        }

        return cur.isWord;
    }

    static class Node {
        Map<Character, Node> next = new HashMap<>();
        boolean isWord = false;

        public Node() {

        }
    }

    public static void main(String[] args) {
        AddAndSearchWord a = new AddAndSearchWord();
        a.addWord("bad");
        a.addWord("dad");
        a.addWord("mad");
        a.addWord("pad");
        a.addWord("dead");

        System.out.println(a.search("dad"));
        System.out.println(a.search("dead"));
        System.out.println(a.search("d.ad"));
    }
}
