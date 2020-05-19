package com.dzavorin.solutions.trie;

import java.util.*;

public class SearchSuggestionsSystem {

    static class Node {
        Node[] next = new Node[26];
        boolean isWord = false;
        char ch = 'a';

        Node() {
        }

        Node(boolean isWord, char ch) {
            this.isWord = isWord;
            this.ch = ch;
        }
    }

    static class Trie {
        Node root = new Node();

        Trie() {
        }

        public void add(String word) {

            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                Node[] nodes = node.next;
                char ch = word.charAt(i);
                Node next = nodes[ch - 'a'];
                if (next == null) {
                    if (i + 1 == word.length()) {
                        next = new Node(true, ch);
                    } else {
                        next = new Node(false, ch);
                    }
                    nodes[ch - 'a'] = next;
                } else if (i + 1 == word.length()) {
                    next.isWord = true;
                }

                node = next;
            }

        }

        private Node getLast(String prefix) {
            Node node = root;
            for (int i = 0; i < prefix.length(); i++) {
                node = node.next[prefix.charAt(i) - 'a'];
                if (node == null) {
                    return null;
                }
            }
            return node;
        }

        public List<String> getThreeMin(String prefix) {
            Node node = getLast(prefix);
            List<String> res = new ArrayList<>();

            if (node == null) {
                return res;
            }

            if (node.isWord) {
                res.add(prefix);
            }

//            to search for minimum length words
//            Map<String, List<Node>> nodes = new HashMap<>();
//            nodes.put(prefix, Arrays.asList(node.next));
//            return bfs(nodes, res);

//            to search for minimum lexicographically
            return dfs(prefix, node.next, res);
        }

        private List<String> dfs(String prefix, Node[] nodes, List<String> res) {
            for (Node n : nodes) {
                if (res.size() == 3) {
                    return res;
                }
                if (n != null) {
                    String nextPrefix = prefix + n.ch;

                    if (n.isWord) {
                        res.add(nextPrefix);
                    }
                    if (res.size() == 3) {
                        return res;
                    }
                    dfs(nextPrefix, n.next, res);
                }
            }
            return res;
        }


        private List<String> bfs(Map<String, List<Node>> map, List<String> res) {
            Map<String, List<Node>> nextBfs = new HashMap<>();
            for (Map.Entry<String, List<Node>> entry : map.entrySet()) {
                String prefix = entry.getKey();
                List<Node> nodes = entry.getValue();
                for (Node n : nodes) {
                    if (n != null) {
                        String nextPrefix = prefix + n.ch;

                        if (n.isWord) {
                            res.add(nextPrefix);
                        }
                        if (res.size() == 3) {
                            return res;
                        }
                        nextBfs.put(nextPrefix, Arrays.asList(n.next));
                    }
                }
            }

            return nextBfs.isEmpty() ? res : bfs(nextBfs, res);
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();

        for (String product : products) {
            trie.add(product);
        }

        List<List<String>> res = new ArrayList<>();

        for (int i = 1; i <= searchWord.length(); i++) {
            res.add(trie.getThreeMin(searchWord.substring(0, i)));
        }

        return res;
    }

    public List<List<String>> suggestedProducts2(String[] products, String searchWord) {
        PriorityQueue<String> pq = new PriorityQueue<>(3);
        List<List<String>> res = new ArrayList<>();

        for (int i = 1; i <= searchWord.length(); i++) {
            String sub = searchWord.substring(0, i);
            for (String s : products) {
                if (s.startsWith(sub)) {
                    pq.offer(s);
                }
            }
            List<String> list = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                if (pq.peek() != null) {
                    list.add(pq.poll());
                }
            }
            pq.clear();
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SearchSuggestionsSystem().suggestedProducts(
                new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse"
        ));
    }

}
