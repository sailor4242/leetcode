package com.dzavorin.solutions.dfs;

public class LetterTilePossibilities {

    public int numTilePossibilities(String tiles) {
        char[] counts = new char[26];
        for (int i = 0; i < tiles.length(); i++) {
            counts[tiles.charAt(i) - 'A']++;
        }
        return rec(counts, 0, tiles.length());
    }

    public int rec(char[] counts, int index, int max) {
        if (index >= max) return 0;

        int total = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                counts[i]--;
                total += 1 + rec(counts, index + 1, max);
                counts[i]++;
            }
        }
        return total;
    }

}
