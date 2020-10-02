package com.dzavorin.solutions;

import java.util.*;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> res = new ArrayList<>();

        dfs(candidates, target, res, new LinkedList<>(), 0, 0);

        return res;
    }

    private void dfs(int[] candidates, int target,
                     List<List<Integer>> res,
                     LinkedList<Integer> list, int sum, int begin) {

        if (sum > target) {
            return;
        } else if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            int c = candidates[i];
            if (sum + c <= target) {
                list.add(c);
                dfs(candidates, target, res, list, sum + c, i);
                list.removeLast();
            }
        }
    }

    /////

    public int[] gcd(int[] candidates) {
        Arrays.sort(candidates);
        int n = candidates.length;
        int[] gcd = new int[n];
        gcd[n - 1] = candidates[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            gcd[i] = gcd(candidates[i], gcd[i + 1]);
        }
        return gcd;
    }

    public int gcd(int a, int b) {
        int c;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new CombinationSum().combinationSum(new int[]{2,3,5}, 8));
        System.out.println(Arrays.toString(new CombinationSum().gcd(new int[] {2, 3, 6})));
    }

}
