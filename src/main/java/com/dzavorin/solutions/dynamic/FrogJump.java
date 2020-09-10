package com.dzavorin.solutions.dynamic;

import java.util.*;

public class FrogJump {

    public boolean canCross(int[] stones) {
        if (stones.length <= 1) {
            return true;
        }
        if (stones[1] - stones[0] > 1) {
            return false;
        }

        Boolean[][] memo = new Boolean[stones.length][stones.length + 1];

        return dfs(stones, 1, stones[1] - stones[0], memo);
    }

    private boolean dfs(int[] stones, int i, int k, Boolean[][] memo) {

        if (i >= stones.length - 1) {
            return false;
        }
        if (memo[i][k] != null) {    // Using boolean cache in reverse way as initial values are false!
            return memo[i][k];
        }

        for (int j = i + 1; j < stones.length; j++) {

            int nextJumpLength = stones[j] - stones[i];
            if (nextJumpLength >= k - 1 && nextJumpLength <= k + 1) {

                if (dfs(stones, j, nextJumpLength, memo)) {
                    return true;
                }

            } else if (nextJumpLength > k + 1) {
                break;
            }
        }

        memo[i][k] = true;
        return false;
    }

    public boolean canCross2(int[] stones) {

        if (stones[1] != 1) return false;

        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], i);
        }
        queue.add(new int[]{1, 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == stones.length - 1) {
                return true;
            }

            int step = cur[1];
            int pos = stones[cur[0]];
            if (!visited.add(step + " " + pos)) {
                continue;
            }

            for (int k = step - 1; k <= step + 1; k++) {
                if (map.containsKey(pos + k)) {
                    queue.add(new int[]{map.get(pos + k), k});
                }
            }
        }
        return false;
    }

    public boolean canCross3(int[] stones) {
        if (stones[1] != 1) {
            return false;
        }

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int stone : stones) {
            map.put(stone, new HashSet<>());
        }

        map.get(1).add(1);

        for (int stone : stones) {
            if (stone == 0) {
                continue;
            }

            Set<Integer> preJumps = map.get(stone);
            for (int preJump : preJumps) {
                int jump;
                for (int k = -1; k <= 1; k++) {
                    jump = preJump + k;
                    if (jump > 0 && map.containsKey(stone + jump)) {
                        map.get(stone + jump).add(jump);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() != 0;
    }

    public static void main(String[] args) {
        System.out.println(new FrogJump().canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(new FrogJump().canCross(new int[]{0,1,2,3,4,8,9,11}));
    }

}
