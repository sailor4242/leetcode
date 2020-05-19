package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HappyNumber {
    public boolean isHappy(int n) {
        Map<Integer, Integer> p = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            p.put(i, i*i);
        }
        Set<Integer> seen = new HashSet<>();
        while (!seen.contains(n)) {
            seen.add(n);
            char[] ch = String.valueOf(n).toCharArray();
            int j = 0;
            for (char c : ch) {
                j += p.get(c - 48);
            }
            if (j == 1) {
                return true;
            } else {
                n = j;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappy(19));
    }
}
