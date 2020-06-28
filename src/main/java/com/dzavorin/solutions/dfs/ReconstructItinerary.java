package com.dzavorin.solutions.dfs;

import java.util.*;

public class ReconstructItinerary {


    HashMap<String, PriorityQueue<String>> map = new HashMap<>();
    LinkedList<String> res = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            if (!map.containsKey(ticket.get(0))) {
                PriorityQueue<String> q = new PriorityQueue<>();
                map.put(ticket.get(0), q);
            }
            map.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return res;
    }

    public void dfs(String s) {
        PriorityQueue<String> q = map.get(s);

        while (q != null && !q.isEmpty()) {
            dfs(q.poll());
        }
        res.addFirst(s);
    }


    public static void main(String[] args) {
        System.out.println(new ReconstructItinerary().findItinerary(
                List.of(List.of("EZE", "TIA"), List.of("EZE", "HBA"), List.of("AXA", "TIA"), List.of("JFK", "AXA"), List.of("ANU", "JFK"), List.of("ADL", "ANU"), List.of("TIA", "AUA"), List.of("ANU", "AUA"), List.of("ADL", "EZE"), List.of("ADL", "EZE"), List.of("EZE", "ADL"), List.of("AXA", "EZE"), List.of("AUA", "AXA"), List.of("JFK", "AXA"), List.of("AXA", "AUA"), List.of("AUA", "ADL"), List.of("ANU", "EZE"), List.of("TIA", "ADL"), List.of("EZE", "ANU"), List.of("AUA", "ANU"))
        ));
        // JFK, AXA, AUA, ADL, ANU, AUA, ANU, EZE, ADL, EZE, ANU, JFK, AXA, EZE, TIA, AUA, AXA, TIA, ADL, EZE, HBA
        //"JFK","AXA","AUA","ADL","ANU","AUA","ANU","EZE","ADL","EZE","ANU","JFK","AXA","EZE","TIA","AUA","AXA","TIA","ADL","EZE","HBA"
    }
}
