package com.dzavorin.solutions.bfs;

import java.util.*;

public class QueensThatCanAttackTheKing {

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<>();

        Map<Integer, Set<Integer>> queensMap = new HashMap<>();

        int xmax = king[0];
        int ymax = king[1];

        for (int[] q : queens) {
            queensMap.putIfAbsent(q[0], new HashSet<>());
            queensMap.get(q[0]).add(q[1]);

            xmax = Math.max(xmax, q[0]);
            ymax = Math.max(ymax, q[1]);
        }

        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[] {0, -1});
        list.add(new int[] {-1, -1});
        list.add(new int[] {-1, 0});
        list.add(new int[] {-1, 1});
        list.add(new int[] {0, 1});
        list.add(new int[] {1, 1});
        list.add(new int[] {1, 0});
        list.add(new int[] {1, -1});

        int x = king[0];
        int y = king[1];

        while (!list.isEmpty()) {

            int[] cur = list.poll();

            int xi = x + cur[0];
            int yj = y + cur[1];

            if (!(xi >= 0 && xi <= xmax
                    && yj >= 0 && yj <= ymax)) {
                continue;
            }

            if (queensMap.containsKey(xi) && queensMap.get(xi).contains(yj)) {

                queensMap.get(xi).remove(yj);
                res.add(Arrays.asList(xi, yj));

            } else {
                for (int i = 0; i < cur.length; i++) {
                    if (cur[i] > 0) {
                        cur[i]++;
                    } else if (cur[i] < 0) {
                        cur[i]--;
                    }
                }
                list.add(cur);
            }

        }

        return res;
    }

}
