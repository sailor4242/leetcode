package com.dzavorin.solutions.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestHappyString {

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> list = new PriorityQueue<>(Comparator.comparing(p -> -p.n));
        list.add(new Pair('a', a));
        list.add(new Pair('b', b));
        list.add(new Pair('c', c));

        String res = "";
        char prev = 0;
        Pair l = null;

        while (!list.isEmpty()) {
            Pair pair = list.poll();

            if (l != null && l.n > 0) {
                list.add(l);
            }

            if (prev == pair.c) {
                break;
            }

            while (pair.n > 0) {
                boolean b2 = res.length() - 2 >= 0 && res.charAt(res.length() - 2) == pair.c;
                boolean b1 = res.length() - 1 >= 0 && res.charAt(res.length() - 1) == pair.c;

                if (b1 & b2) {
                    break;
                } else {
                    if (list.peek() != null && list.peek().n - pair.n > 0) {
                        prev = pair.c;
                        res += pair.c;
                        pair.n--;
                        break;
                    }
                    prev = pair.c;
                    res += pair.c;
                    pair.n--;
                }
            }
            l = pair;
        }

        return res;
    }

    static class Pair {
        char c;
        int n;

        Pair(char c, int n) {
            this.n = n;
            this.c = c;
        }
    }

    public int solution(String S) {
        Integer[][][] distances = new Integer[26][26][2];

        for (int i = 1; i < S.length(); i++) {
            int first = S.charAt(i - 1) - 'a';
            int second = S.charAt(i) - 'a';
            Integer[] cur = distances[first][second];
            if (cur[0] == null) {
                cur[0] = i - 1;
                cur[1] = 0;
            } else {
                cur[1] = i - 1 - cur[0];
            }
        }

        int res = -1;

        for (Integer[][] dist : distances) {
            for (Integer[] integers : dist) {
                if (integers[1] != null && integers[1] > 0) {
                    res = Math.max(res, integers[1]);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestHappyString().solution("aaa"));
    }
}
