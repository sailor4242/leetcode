package com.dzavorin.solutions.bfs;

import java.util.*;

public class WordLadder2 {

    public List<List<String>> findLadders(String beginWord,
                                          String endWord, List<String> wordList) {


        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        for (String b : wordList) {
            for (int i = 0; i < b.length(); i++) {
                String word = b.substring(0, i) + '*' + b.substring(i + 1, b.length());
                map.putIfAbsent(word, new ArrayList<>());
                map.get(word).add(b);
            }

        }

        LinkedList<Pair3<String, Integer, List<String>>> list = new LinkedList<>();

        List<String> next = new ArrayList<>();
        next.add(beginWord);
        list.add(new Pair3<>(beginWord, 0, next));

        while (!list.isEmpty()) {
            LinkedList<Pair3<String, Integer, List<String>>> nextList = new LinkedList<>();
            Set<String> toRemove = new HashSet<>();

            while (!list.isEmpty()) {
                Pair3<String, Integer, List<String>> p = list.removeFirst();

                if (p.getFirst().equals(endWord)) {
                    res.add(p.getThird());
                    continue;
                }

                char[] ch = p.getFirst().toCharArray();
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
                        List<String> nextL = new ArrayList<>(p.getThird());
                        nextL.add(w);
                        nextList.add(new Pair3<>(w, p.getSecond() + 1, nextL));
                    }

                    toRemove.add(word);

                }

            }
            if (!res.isEmpty()) {
                return res;
            }

            for (String r : toRemove) {
                map.put(r, null);
            }

            list = nextList;
        }
        return res;
    }

    static class Pair3<A, B, C> {
        A a;
        B b;
        C c;

        Pair3(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        A getFirst() {
            return a;
        }

        B getSecond() {
            return b;
        }

        C getThird() {
            return c;
        }
    }

}
