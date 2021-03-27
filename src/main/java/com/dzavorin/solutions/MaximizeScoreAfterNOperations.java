package com.dzavorin.solutions;

import java.util.HashSet;
import java.util.PriorityQueue;

public class MaximizeScoreAfterNOperations {

    public int maxScore(int[] nums) {
        PriorityQueue<Temp> pq = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                pq.offer(new Temp(i, j, nums[i], nums[j]));
            }
        }

        int n = nums.length / 2;
        int score = 0;
        boolean[] used = new boolean[nums.length];

        while (!pq.isEmpty()) {
            var item = pq.poll();
            if (!used[item.i] && !used[item.j]) {
                score += n-- * item.gcd;
                used[item.i] = true;
                used[item.j] = true;
            }
        }

        return score;
    }


    class Temp implements Comparable<Temp> {
        int gcd;
        int i, j;
        int v1, v2;


        public Temp(int i, int j, int v1, int v2) {
            this.gcd = gcd(v1, v2);
            this.i = i;
            this.j = j;
            this.v1 = v1;
            this.v2 = v2;
        }

        public int compareTo(Temp other) {
            if (this.gcd == other.gcd)
                return other.v1 - this.v1;

            return other.gcd - this.gcd;
        }

    }

    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    //// TLE, naive dfs

    public int maxScore2(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        return dfs(nums, 0, 0, visited);
    }

    private int dfs(int[] nums, int n, int score, boolean[] visited) {
        if (n * 2 == nums.length) {
            return score;
        }
        String key = n + "_" + score;


        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            for (int j = 0; j < nums.length; j++) {
                if (i == j || visited[j]) continue;

                int gcd = gcd(nums[i], nums[j]);
                visited[i] = true;
                visited[j] = true;
                res = Math.max(res, dfs(nums, n + 1, score + (n + 1) * gcd, visited));
                visited[i] = false;
                visited[j] = false;
            }
        }

        return res;
    }

}
