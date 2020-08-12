package com.dzavorin.solutions.math;

public class PowerOfTwo {

    // same as bitcount
    public boolean isPowerOfTwo(int n) {
        int c = 0;
        String str = Integer.toBinaryString(n);
        boolean first = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                c++;
                if (i == 0) {
                    first = true;
                }
            }
            if (c > 1) {
                return false;
            }
        }
        if (c == 1 && str.length() == 32 && first) {
            return false;
        } else return c == 1;
    }

    public boolean isPowerOfTwo2(int n) {
        return n > 0 && ((n & (n - 1)) == 0);
    }

    public boolean isPowerOfTwo3(int n) {
        return n > 0 && Integer.bitCount(n) == 1;
    }

    public boolean isPowerOfTwo4(int n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println(new PowerOfTwo().isPowerOfTwo(16));
        System.out.println(new PowerOfTwo().isPowerOfTwo(1));
        System.out.println(new PowerOfTwo().isPowerOfTwo(-2147483648));
        System.out.println(new PowerOfTwo().isPowerOfTwo(-15));
    }
}
