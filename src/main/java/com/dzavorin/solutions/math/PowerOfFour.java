package com.dzavorin.solutions.math;

public class PowerOfFour {
    public boolean isPowerOfFour2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }

    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        while (num % 4 == 0) num /= 4;
        return num == 1;
    }
}
