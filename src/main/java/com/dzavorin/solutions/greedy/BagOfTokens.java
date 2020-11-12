package com.dzavorin.solutions.greedy;

import java.util.Arrays;

public class BagOfTokens {

    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int score = 0;

        int lo = 0;
        int hi = tokens.length - 1;

        while (lo <= hi) {

            if (P < tokens[lo] && score > 0 && hi != lo) {
                P += tokens[hi];
                score--;
                hi--;
            } else if (P >= tokens[lo]) {
                P -= tokens[lo];
                lo++;
                score++;
            }
        }

        return score;
    }

    public static void main(String[] args) {
        System.out.println(new BagOfTokens().bagOfTokensScore(new int[]{17,41,47,51,57}, 31));
        System.out.println(new BagOfTokens().bagOfTokensScore(new int[]{100,200,300,400}, 200));
        System.out.println(new BagOfTokens().bagOfTokensScore(new int[]{100,200}, 150));
        System.out.println(new BagOfTokens().bagOfTokensScore(new int[]{100}, 50));
    }

}
