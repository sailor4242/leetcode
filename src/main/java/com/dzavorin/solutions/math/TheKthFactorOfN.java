package com.dzavorin.solutions.math;

public class TheKthFactorOfN {

    // O(n)
    public int kthFactor(int n, int k) {
        for (int i = 1; i < n; i++) {
            if (n % i == 0 && --k == 0)
                return i;
        }
        return -1;
    }

    // O(sqrt(n))
    public int kthFactor2(int n, int k) {
        for (int i = 1; i * i < n; i++) {
            if (n % i == 0 && --k == 0)
                return i;
        }
        for (int i = (int) Math.sqrt(n); i >= 1; i--) {
            if (n % i == 0 && --k == 0)
                return n / i;
        }
        return -1;
    }

}
