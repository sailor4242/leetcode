package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckArrayFormationThroughConcatenation {

    public boolean canFormArray(int[] arr, int[][] pieces) {

        Map<Integer, List<int[]>> map = new HashMap<>();
        Map<Integer, List<Boolean>> visited = new HashMap<>();

        for (int[] p : pieces) {
            map.putIfAbsent(p[0], new ArrayList<>());
            visited.putIfAbsent(p[0], new ArrayList<>());
            map.get(p[0]).add(p);
            visited.get(p[0]).add(false);
        }

        return dp(arr, map, 0, visited);
    }

    private boolean dp(int[] arr, Map<Integer, List<int[]>> map, int i, Map<Integer, List<Boolean>> visited) {
        if (i == arr.length) return true;


        List<int[]> pieces = map.get(arr[i]);
        if (pieces != null) {

            for (int k = 0; k < pieces.size(); k++) {
                List<Boolean> v = visited.get(arr[i]);
                if (v.get(k) == true) continue;

                int[] p = pieces.get(k);
                if (p.length <= arr.length - i
                        && checkArray(arr, p, i)) {
                    v.set(k, true);
                    if (dp(arr, map, i + p.length, visited)) {
                        return true;
                    }
                    v.set(k, false);
                }
            }

        }

        return false;
    }

    private boolean checkArray(int[] arr, int[] p, int i) {
        for (int j = 0; j < p.length; j++) {
            if (arr[i++] != p[j]) {
                return false;
            }
        }
        return true;
    }

    ////


    public boolean canFormArray2(int[] arr, int[][] pieces) {
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], pieces[i]);
        }
        for (int i = 0; i < arr.length; ) {
            int num = arr[i];
            if (!map.containsKey(num)) {
                return false;
            }
            int[] nums = map.get(num);
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != arr[i++]) {
                    return false;
                }
            }
        }
        return true;
    }

}