package com.dzavorin.solutions;


public class RandomPickWithWeight {
    int[] w;
    int s;

    public RandomPickWithWeight(int[] w) {
        int s = 0;
        int[] ww = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            s += w[i];
            ww[i] = s;
        }
        this.w = ww;
        this.s = s;
    }

    public int pickIndex() {
        int n = (int) (Math.random() * s) + 1;

        int lo = 0;
        int hi = w.length - 1;

        while (lo < hi) {
            int m = lo + (hi - lo)/2;
            if (w[m] < n) {
                lo = m + 1;
            } else {
                hi = m;
            }
        }

        return lo;
    }

    public static void main(String[] args) {
        RandomPickWithWeight r = new RandomPickWithWeight(new int[]{1});
        System.out.println(r.pickIndex());
    }
}
