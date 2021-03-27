package com.dzavorin.solutions.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartitioning {

    public List<List<String>> partition(String s) {

        List<List<String>> res = new ArrayList<>();

        dfs(s, 0, new LinkedList<>(), res);

        return res;
    }

    private void dfs(String s, int j, LinkedList<String> cur, List<List<String>> res) {
        if (j == s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = j; i < s.length(); i++) {

            if (isPalindrome(s, j, i)) {
                cur.add(s.substring(j, i + 1));
                dfs(s, i + 1, cur, res);
                cur.removeLast();
            }

        }

    }

    private boolean isPalindrome(String s, int i, int j) {
        if (i == j) {
            return true;
        }
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }

        return true;
    }

}
