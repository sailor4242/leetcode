package com.dzavorin.solutions.greedy;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class Dota2Senate {

    public String predictPartyVictory(String senate) {
        if (senate.length() == 1) {
            return senate.charAt(0) == 'D' ? "Dire" : "Radiant";
        }

        Map<Character, TreeSet<Integer>> map = new HashMap<>();
        boolean[] banned = new boolean[senate.length()];

        for (int i = 0; i < senate.length(); i++) {
            Character ch = senate.charAt(i);
            map.putIfAbsent(ch, new TreeSet<>());
            map.get(ch).add(i);
        }

        while (!(map.get('R') == null || map.get('R').isEmpty())
                && !(map.get('D') == null || map.get('D').isEmpty())) {

            for (int i = 0; i < senate.length(); i++) {
                if (banned[i]) continue;

                Character ch = senate.charAt(i);
                TreeSet<Integer> list;
                TreeSet<Integer> contrList;
                if (ch == 'R') {
                    contrList = map.get('D');
                    list = map.get('R');
                } else {
                    contrList = map.get('R');
                    list = map.get('D');
                }
                if (contrList.isEmpty() && !list.isEmpty()) {
                    return senate.charAt(list.first()) == 'D' ? "Dire" : "Radiant";
                } else {
                    Integer next = contrList.higher(i);
                    if (next == null) {
                        Integer first = contrList.first();
                        contrList.remove(first);
                        banned[first] = true;
                    } else {
                        banned[next] = true;
                        contrList.remove(next);
                    }
                }
            }
        }

        return (map.get('R') == null || map.get('R').isEmpty()) ? "Dire" : "Radiant";
    }

    public String predictPartyVictory2(String senate) {
        LinkedList<Integer> r = new LinkedList<>();
        LinkedList<Integer> d = new LinkedList<>();

        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                r.add(i);
            } else {
                d.add(i);
            }
        }
        while (d.size() > 0 && r.size() > 0) {
            if (r.getFirst() < d.getFirst()) {
                d.removeFirst();
                r.add(r.removeFirst() + senate.length());
            } else {
                r.removeFirst();
                d.add(d.removeFirst() + senate.length());
            }
        }
        return d.size() > 0 ? "Dire" : "Radiant";
    }

    public static void main(String[] args) {
        System.out.println(new Dota2Senate().predictPartyVictory("D"));
        System.out.println(new Dota2Senate().predictPartyVictory("DD"));
        System.out.println(new Dota2Senate().predictPartyVictory("RRR"));
    }
}
