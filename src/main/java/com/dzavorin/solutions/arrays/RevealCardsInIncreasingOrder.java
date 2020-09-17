package com.dzavorin.solutions.arrays;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class RevealCardsInIncreasingOrder {

    public int[] deckRevealedIncreasing(int[] deck) {
        int N = deck.length;
        Deque<Integer> idxs = new LinkedList();
        for (int i = 0; i < N; ++i)
            idxs.add(i);

        int[] ans = new int[N];
        Arrays.sort(deck);
        for (int card: deck) {
            ans[idxs.pollFirst()] = card;
            if (!idxs.isEmpty())
                idxs.add(idxs.pollFirst());
        }

        return ans;
    }
}
