package com.dzavorin.solutions.graph.djikstra;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree;
import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class OpenTheLock {

    public int openLock(String[] deadends, String target) {
        if (target == null || target.length() != 4) return -1;

        Set<String> dead = new HashSet<>();
        for (String s : deadends) {
            dead.add(s);
        }

        if (dead.contains("0000")) return -1;

        PriorityQueue<Pair<String, Integer>> pq = new PriorityQueue<>(Comparator.comparing(pair -> pair.getValue()));

        pq.add(new Pair("0000", 0));

        Map<String, Integer> memo = new HashMap<>();
        memo.put("0000", 0);

        while (!pq.isEmpty()) {
            var pair = pq.poll();

            char[] arr = pair.getKey().toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char ch = arr[i];
                String str1;
                String str2;
                if (ch == '0') {
                    arr[i] = '1';
                    str1 = new String(arr);
                    arr[i] = '9';
                    str2 = new String(arr);
                } else if (ch == '9') {
                    arr[i] = '0';
                    str1 = new String(arr);
                    arr[i] = '8';
                    str2 = new String(arr);
                } else {
                    arr[i] += 1;
                    str1 = new String(arr);
                    arr[i] -= 2;
                    str2 = new String(arr);
                }
                arr[i] = ch;

                Integer next = pair.getValue() + 1;

                if (!dead.contains(str1) && memo.getOrDefault(str1, Integer.MAX_VALUE) > next) {
                    pq.add(new Pair<>(str1, next));
                    memo.put(str1, next);
                }

                if (!dead.contains(str2) && memo.getOrDefault(str2, Integer.MAX_VALUE) > next) {
                    pq.add(new Pair<>(str2, next));
                    memo.put(str2, next);
                }

            }

        }

        return memo.getOrDefault(target, -1);
    }


    // faster bfs
    public int openLock2(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        for (String s : deadends)
            dead.add(s);

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer("0000");
        visited.add("0000");
        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (dead.contains(cur))
                    continue;
                if (cur.equals(target))
                    return steps;

                for (int j = 0; j < 4; j++) {
                    String cur_add = Add(cur, j);
                    if (!visited.contains(cur_add)) {
                        q.offer(cur_add);
                        visited.add(cur_add);
                    }

                    String cur_minus = Minus(cur, j);
                    if (!visited.contains(cur_minus)) {
                        q.offer(cur_minus);
                        visited.add(cur_minus);
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    public String Add(String s, int i) {
        char[] ch = s.toCharArray();
        if (ch[i] == '9')
            ch[i] = '0';
        else
            ch[i] += 1;

        return new String(ch);
    }

    public String Minus(String s, int i) {
        char[] ch = s.toCharArray();
        if (ch[i] == '0')
            ch[i] = '9';
        else
            ch[i] -= 1;

        return new String(ch);
    }

}
