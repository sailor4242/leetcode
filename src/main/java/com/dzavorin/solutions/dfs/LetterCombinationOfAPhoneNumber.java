package com.dzavorin.solutions.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationOfAPhoneNumber {

    public List<String> letterCombinations(String digits) {

        Map<Character, List<Character>> map = new HashMap<>();

        map.put('2', List.of('a', 'b', 'c'));
        map.put('3', List.of('d', 'e', 'f'));
        map.put('4', List.of('g', 'h', 'i'));
        map.put('5', List.of('j', 'k', 'l'));
        map.put('6', List.of('m', 'n', 'o'));
        map.put('7', List.of('p', 'q', 'r', 's'));
        map.put('8', List.of('t', 'u', 'v'));
        map.put('9', List.of('w', 'x', 'y', 'z'));


        List<String> res = new ArrayList<>();

        if (digits.isEmpty()) return res;

        dfs(digits, map, res, 0, new StringBuilder());

        return res;
    }

    private void dfs(String digits, Map<Character, List<Character>> map,
                     List<String> res, int i, StringBuilder sb) {
        if (i == digits.length()) {
            res.add(sb.toString());
            return;
        }

        char ch = digits.charAt(i);

        List<Character> chars = map.get(ch);
        for (Character c : chars) {
            sb.append(c);
            dfs(digits, map, res, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
