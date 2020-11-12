package com.dzavorin.solutions.dynamic;

import java.util.*;

public class CanIWin {

    /////// bitmask dp

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0)
            return true;
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal)
            return false;

        return win(maxChoosableInteger, desiredTotal, 0, new HashMap<>());
    }

    boolean win(int max, int desiredTotal, int state, Map<Integer, Boolean> memo) {
        if (desiredTotal <= 0)
            return false;
        if (memo.containsKey(state))
            return memo.get(state);

        boolean res = false;

        for (int i = max - 1; i >= 0; i--) {
            if ((1 << i & state) == 0) {
                if (!win(max, desiredTotal - i - 1, 1 << i | state, memo)){
                    res = true;
                    break;
                }
            }
        }

        memo.put(state, res);
        return res;
    }

    //////////////////////// straightforward dp

    private String getKey(boolean[] array, int current, boolean isFirst) {
        String key = Arrays.toString(array);
        return key + "|" + current + "|" + isFirst;
    }

    private boolean winner(boolean[] usedNums, int desiredTotal, int maxChoosableInteger, int current, boolean isFirst, HashMap<String, Boolean> memo) {
        String key = getKey(usedNums, current, isFirst);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (current >= desiredTotal) {
            return !isFirst;
        }

        for (int i = 1; i <= maxChoosableInteger; i++) {
            if (usedNums[i]) {
                continue;
            }
            usedNums[i] = true;
            boolean won = winner(usedNums, desiredTotal, maxChoosableInteger, current + i, !isFirst, memo);
            memo.put(getKey(usedNums, current + i, !isFirst), won);
            usedNums[i] = false;
            if (isFirst && won) {
                return true;
            } else if (!isFirst && !won) {
                return false;
            }
        }
        return !isFirst;
    }

    public boolean canIWin2(int maxChoosableInteger, int desiredTotal) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (sum < desiredTotal) return false;
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        HashMap<String, Boolean> memo = new HashMap<>();
        boolean[] usedNums = new boolean[maxChoosableInteger];
        return winner(usedNums, desiredTotal, maxChoosableInteger, 0, true, memo);
    }

    public static void main(String[] args) {

    }

}
