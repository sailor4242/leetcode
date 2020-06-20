package com.dzavorin.solutions.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupThePeopleGivingTheGroupSize {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int gr = groupSizes[i];
            List<Integer> l = map.get(gr);
            if (l == null) {
                l = new ArrayList<>();
                map.put(gr, l);
            }
            l.add(i);
            if (l.size() == gr) {
                res.add(l);
                map.put(gr, new ArrayList<>());
            }
        }

        return res;
    }
}
