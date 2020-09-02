package com.dzavorin.solutions.bfs;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class MiniumumGeneticMutation {

    public int minMutation(String start, String end, String[] bank) {
        Map<String, List<String>> map = new HashMap<>();

        for (String b : bank) {
            for (int i = 0; i < b.length(); i++) {
                String word = b.substring(0, i) + '*' + b.substring(i + 1, b.length());
                map.putIfAbsent(word, new ArrayList<>());
                map.get(word).add(b);
            }

        }

        LinkedList<Pair<String, Integer>> list = new LinkedList<>();
        list.add(new Pair(start, 0));

        while (!list.isEmpty()) {

            Pair<String, Integer> p = list.removeFirst();

            if (p.getKey().equals(end)) {
                return p.getValue();
            }

            char[] ch = p.getKey().toCharArray();
            for (int i = 0; i < ch.length; i++) {
                char tmp = ch[i];
                ch[i] = '*';
                String word = new String(ch);
                ch[i] = tmp;

                List<String> words = map.get(word);

                if (words == null) {
                    continue;
                }

                for (String w : words) {
                    list.add(new Pair<>(w, p.getValue() + 1));
                }

                map.put(word, null);
            }

        }
        return -1;
    }

}
