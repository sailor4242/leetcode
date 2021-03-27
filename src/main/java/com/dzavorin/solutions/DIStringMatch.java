package com.dzavorin.solutions;

public class DIStringMatch {

    public int[] diStringMatch(String S) {
        int N = S.length();
        int lo = 0, hi = N;
        int[] res = new int[N + 1];
        for (int i = 0; i < N; ++i) {
            if (S.charAt(i) == 'I')
                res[i] = lo++;
            else
                res[i] = hi--;
        }

        res[N] = lo;
        return res;
    }


}
