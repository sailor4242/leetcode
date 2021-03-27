package com.dzavorin.solutions.math;

public class PowerOfThree {

    public boolean isPowerOfThree(int n) {
        if (n < 1) return false;

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }


    ///

    public boolean isPowerOfThree2(int n) {
        return Integer.toString(n, 3).matches("^10*$");
    }


    ////

    public boolean isPowerOfThree3(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }

    ////

    public boolean isPowerOfThree4(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

}
