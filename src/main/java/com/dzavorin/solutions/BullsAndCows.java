package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class BullsAndCows {

    public String getHint(String secret, String guess) {

        int bulls = 0;
        int cows = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < secret.length(); i++) {
            map.put(secret.charAt(i), map.getOrDefault(secret.charAt(i), 0) + 1);
        }

        for (int i = 0; i < secret.length(); i++) {
            Character chg = guess.charAt(i);
            Integer count = map.get(chg);
            if (secret.charAt(i) == chg) {
                bulls++;
                if (count <= 0) {
                    cows--;
                } else {
                    map.put(chg, count - 1);
                }
            } else if (map.containsKey(chg) && count > 0) {
                map.put(chg, count - 1);
                cows++;
            }
        }

        return bulls + "A" + cows + "B";
    }

    public String getHint2(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] secretFreq = new int[10];

        int[] guessFreq = new int[10];

        // O(n)
        for (int i = 0; i < secret.length(); i++) {
            char secretChar = secret.charAt(i);

            char guessChar = guess.charAt(i);

            if (secretChar == guessChar) {
                bulls++;
            } else {
                secretFreq[secretChar - '0']++;

                guessFreq[guessChar - '0']++;
            }
        }

        // O(1)
        for (int i = 0; i < 10; i++) {
            cows += Math.min(secretFreq[i], guessFreq[i]);
        }

        return bulls + "A" + cows + "B";
    }
}
