package com.dzavorin.solutions.amazon;

import java.util.*;

public class AnalyzeUserWebsiteVisitPattern {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int[][] timestampOrder = new int[timestamp.length][2];
        for (int i = 0; i < timestamp.length; i++) {
            timestampOrder[i][0] = timestamp[i];
            timestampOrder[i][1] = i;
        }

        Arrays.sort(timestampOrder, Comparator.comparing(arr -> arr[0]));

        Map<String, List<String>> userVisited = new HashMap<>();

        for (int i = 0; i < timestampOrder.length; i++) {
            int j = timestampOrder[i][1];
            String user = username[j];
            String site = website[j];

            userVisited.putIfAbsent(user, new ArrayList<>());
            userVisited.get(user).add(site);
        }

        Map<String, Set<String>> sequenceCount = new HashMap<>();

        for (String user : userVisited.keySet()) {
            List<String> sites = userVisited.get(user);
            dfs(user, sequenceCount, sites, 0, new LinkedList<>());
        }

        PriorityQueue<String> pq = new PriorityQueue<>();
        int max = 0;
        for (String key : sequenceCount.keySet()) {
            int size = sequenceCount.get(key).size();
            if (size > max) {
                pq = new PriorityQueue<>();
                pq.add(key);
                max = size;
            } else if (size == max) {
                pq.add(key);
            }
        }

        return Arrays.asList(pq.poll().split(" "));
    }

    private void dfs(String user,
                     Map<String, Set<String>> sequenceCount,
                     List<String> sites,
                     int j,
                     LinkedList<String> cur) {
        if (cur.size() == 3) {
            String sequence = cur.get(0) + " " + cur.get(1) + " " + cur.get(2);
            sequenceCount.putIfAbsent(sequence, new HashSet<>());
            sequenceCount.get(sequence).add(user);
        } else if (cur.size() > 3 || j == sites.size()) {
            return;
        }

        for (int i = j ; i < sites.size(); i++) {
            cur.add(sites.get(i));
            dfs(user, sequenceCount, sites, i + 1, cur);
            cur.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(new AnalyzeUserWebsiteVisitPattern().mostVisitedPattern(
                new String[]{"zkiikgv","zkiikgv","zkiikgv","zkiikgv"},
                new int[]{436363475,710406388,386655081,797150921},
                new String[]{"wnaaxbfhxp","mryxsjc","oz","wlarkzzqht"}
        ));
    }

}
