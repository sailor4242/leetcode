package com.dzavorin.solutions.dynamic;

import java.util.HashMap;
import java.util.Map;

public class StoneGame4 {

    public boolean winnerSquareGame(int n) {
        Map<Integer, Boolean> map = new HashMap<>();
        map.put(0, false);
        map.put(1, true);
        return winnerSquareGame(n, map);
    }

    public boolean winnerSquareGame(int n, Map<Integer, Boolean> map) {

        if (map.containsKey(n)) {
            return map.get(n);
        }

        int i = (int) Math.sqrt(n);
        while (i >= 1) {
            if (!winnerSquareGame(n - i * i, map)) {
                map.put(n, true);
                return true;
            }
            i--;
        }
        map.put(n, false);
        return false;
    }

}
