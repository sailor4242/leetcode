package com.dzavorin.solutions.dfs;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {

    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(S, 0, new StringBuilder(), res);
        return res;
    }

    private void dfs(String s, int i, StringBuilder sb, List<String> res) {
        if (i == s.length()) {
            res.add(sb.toString());
            return;
        }

        char ch = s.charAt(i);
        if (ch >= 'A' && ch <= 'z') {
            sb.append(Character.toLowerCase(ch));
            dfs(s, i + 1, sb, res);

            sb.deleteCharAt(sb.length() - 1);

            sb.append(Character.toUpperCase(ch));
            dfs(s, i + 1, sb, res);
        } else {
            sb.append(ch);
            dfs(s, i + 1, sb, res);

        }

        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {
        System.out.println(new LetterCasePermutation().letterCasePermutation("a1b2"));
    }

}
