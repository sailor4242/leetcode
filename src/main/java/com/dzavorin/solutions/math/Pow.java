package com.dzavorin.solutions.math;

public class Pow {

    public double myPow(double x, int n) {
        return n < 0
                ? 1.0 / pow(x, -n)
                : pow(x, n);
    }

    private double pow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            return pow(x * x, n / 2);
        }
        return x * pow(x * x, n / 2);
    }

}
