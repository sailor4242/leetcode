package com.dzavorin.solutions;

import java.util.*;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0) {
            return result;
        }
        helper(candidates, result, new ArrayList<>(),0, target);
        return result;
    }

    private void helper(int [] candidates, List<List<Integer>> result, List<Integer> tempList, int pos, int sumLeft){
        if (sumLeft == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = pos; i < candidates.length; i++){
            if (sumLeft - candidates[i] < 0) {
                continue;
            }
            tempList.add(candidates[i]);
            helper(candidates,result, tempList, i, sumLeft - candidates[i]);
            tempList.remove(tempList.size() - 1);
        }
    }

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
