package com.dzavorin.solutions;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree;
import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class RearrangeStringsKDistanceApart {

    public String rearrange(String s, int k) {

        Map<Character, Integer> counts = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Pair<Character, Integer>> pq =
                new PriorityQueue<>(Comparator.comparing(pair -> - pair.getValue()));

        counts.forEach((c, v) -> pq.add(new Pair<>(c, v)));

        Set<Character> kRecent = new HashSet<>();
        LinkedList<Character> kRecentList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        while (dfs(pq, kRecent, kRecentList, k - 1, sb)) {

        }

        return sb.length() == s.length() ? sb.toString() : "";
    }

    private boolean dfs(PriorityQueue<Pair<Character, Integer>> pq,
                                         Set<Character> kRecent,
                                         LinkedList<Character> kRecentList,
                                         int k,
                                         StringBuilder sb) {
            if (pq.isEmpty()) return false;

            Pair<Character, Integer> cur = pq.poll();

            Character curChar = cur.getKey();

            if (kRecent.contains(curChar)) {
                boolean found = dfs(pq, kRecent, kRecentList, k, sb);
                if (found) {
                    pq.add(cur);
                } else {
                    return false;
                }
            } else {
                sb.append(curChar);

                if (kRecentList.size() == k) {
                    kRecent.remove(kRecentList.removeFirst());
                }

                kRecentList.add(curChar);
                kRecent.add(curChar);

                if (cur.getValue() - 1 > 0) {
                    pq.add(new Pair<>(curChar, cur.getValue() - 1));
                }
            }
            return true;
    }

    public static void main(String[] args) {
        System.out.println(new RearrangeStringsKDistanceApart().rearrange("aabbcc", 3));
        System.out.println(new RearrangeStringsKDistanceApart().rearrange("aaabc", 3));
        System.out.println(new RearrangeStringsKDistanceApart().rearrange("aaabbccd", 3));
        System.out.println(new RearrangeStringsKDistanceApart().rearrange("aaadbbcc", 2));
    }
}
