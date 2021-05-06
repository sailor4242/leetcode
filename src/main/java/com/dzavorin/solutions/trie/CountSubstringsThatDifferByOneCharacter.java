package com.dzavorin.solutions.trie;

import java.util.HashMap;
import java.util.Map;

public class CountSubstringsThatDifferByOneCharacter {

    public int countSubstrings(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++)
            res += helper(s, t, i, 0);
        for (int j = 1; j < t.length(); j++)
            res += helper(s, t, 0, j);
        return res;
    }


    public int helper(String s, String t, int i, int j) {
        int res = 0, pre = 0, cur = 0;
        for (int n = s.length(), m = t.length(); i < n && j < m; i++, j++) {
            cur++;
            if (s.charAt(i) != t.charAt(j)) {
                pre = cur;
                cur = 0;
            }
            res += pre;
        }
        return res;
    }

    ///

    public int countSubstrings2(String s, String t) {
        int n = s.length(), m = t.length(), ans = 0;
        int[][][] dp = new int[n + 1][m + 1][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1][0] = dp[i][j][0] + 1;
                    dp[i + 1][j + 1][1] = dp[i][j][1];
                } else {
                    dp[i + 1][j + 1][1] = dp[i][j][0] + 1;
                }
                ans += dp[i + 1][j + 1][1];
            }
        }
        return ans;
    }

    ////

    public int countSubstrings3(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                int x = i;
                int y = j;
                int d = 0;
                while (x < s.length() && y < t.length()) {
                    if (s.charAt(x) != t.charAt(y)) {
                        d++;
                    }
                    if (d == 1) {
                        res++;
                    }
                    if (d == 2) {
                        break;
                    }
                    x++;
                    y++;
                }
            }
        }
        return res;
    }

    ///

    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        int count;
        public TrieNode() {
            this.children = new HashMap<>();
            this.isWord = false;
            this.count = 0;
        }
    }

    static class Trie {
        TrieNode root = new TrieNode();

        public void add(String s, int lo, int hi) {
            TrieNode cur = this.root;
            for (int i = lo; i < hi; i++) {
                char ch = s.charAt(i);
                cur.children.putIfAbsent(ch, new TrieNode());
                cur = cur.children.get(ch);
            }
            cur.count++;
            cur.isWord = true;
        }

        public int containsWithOneDiff(String s, int lo, int hi, boolean hasChangedChar, TrieNode root) {
            if (lo == hi) {
                return hasChangedChar && root.isWord ? root.count : 0;
            }

            int res = 0;
            char curChar = s.charAt(lo);
            for (char ch : root.children.keySet()) {
                if (ch != curChar && !hasChangedChar) {
                    res += this.containsWithOneDiff(s, lo + 1, hi,true, root.children.get(ch));
                } else if (ch == curChar) {
                    res += this.containsWithOneDiff(s, lo + 1, hi, hasChangedChar, root.children.get(ch));
                }
            }
            return res;
        }
    }

    public int countSubstrings4(String s, String t) {
        Trie tree = new Trie();
        for (int i = 1; i <= t.length(); i++) {
            for (int j = 0; j < i; j++) {
                tree.add(t, j, i);
            }
        }

        int res = 0;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                res += tree.containsWithOneDiff(s, j, i, false, tree.root);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountSubstringsThatDifferByOneCharacter().countSubstrings4("aba", "baba"));
        System.out.println(new CountSubstringsThatDifferByOneCharacter().countSubstrings4("ab", "bb"));
        System.out.println(new CountSubstringsThatDifferByOneCharacter().countSubstrings4("a", "b"));
        System.out.println(new CountSubstringsThatDifferByOneCharacter().countSubstrings4("a", "a"));
    }

}
