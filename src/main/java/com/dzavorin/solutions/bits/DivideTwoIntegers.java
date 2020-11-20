package com.dzavorin.solutions.bits;

public class DivideTwoIntegers {

    //   The key observation is that the quotient of a division is just the number of times that we can subtract the divisor
//   from the dividend without making it negative.
//
//   Suppose dividend = 15 and divisor = 3, 15 - 3 > 0. We now try to subtract more by shifting 3 to the left by 1 bit (6).
//   Since 15 - 6 > 0, shift 6 again to 12. Now 15 - 12 > 0, shift 12 again to 24, which is larger than 15.
//   So we can at most subtract 12 from 15. Since 12 is obtained by shifting 3 to left twice, it is 1 << 2 = 4 times of 3.
//   We add 4 to an answer variable (initialized to be 0). The above process is like 15 = 3 * 4 + 3.
//   We now get part of the quotient (4), with a remaining dividend 3.
//
//    Then we repeat the above process by subtracting divisor = 3 from the remaining dividend = 3 and obtain 0.
//    We are done. In this case, no shift happens. We simply add 1 << 0 = 1 to the answer variable.
//
//    This is the full algorithm to perform division using bit manipulations.
//    The sign also needs to be taken into consideration.
//    And we still need to handle one overflow case: dividend = INT_MIN and divisor = -1

//  dvd - dividend, dvs - divisor
    public int divide(int dvd, int dvs) {
        if (dvs == 0 || dvd == Integer.MIN_VALUE && dvs == -1) return Integer.MAX_VALUE;  // remember this

        int res = 0;
        boolean isNeg = (dvd > 0) ^ (dvs > 0);

        long d = Math.abs((long) dvd);
        long s = Math.abs((long) dvs);  // long must be convereted first;
        while (d >= s) {
            long tmp = s;
            long mul = 1;
            while (d >= tmp << 1) {
                tmp <<= 1;
                mul <<= 1;
            }
            d -= tmp;
            res += mul;
        }
        return isNeg ? -res : res;
    }

    public static void main(String[] args) {
        System.out.println(3 << 3); // 24 // x2
    }

    /// brute forse substraction

    public int divide2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE; // Case: Result out of range

        // Case: When divisor is 1 or -1, i.e. no division is needed
        if (divisor == 1) return dividend;
        if (divisor == -1)
            return -dividend; // In case dividend is Integer.MIN_VALUE, and there's an overflow, we've already handled it above

        // If divisor is Integer.MIN_VALUE we can return 1 if dividend is also Integer.MIN_VALUE
        // otherwise 0 because every other integer would be smaller than 2147483648 and dividing by -2147483648 would give us 0.
        if (divisor == Integer.MIN_VALUE) return dividend == Integer.MIN_VALUE ? 1 : 0;

        boolean isNeg = (dividend > 0) ^ (divisor > 0); // To check if just one is negative.

        // Now we still have a case if dividend is out of range, and we convert it to positive, it would cause overflow, so we add a 1 and maintain a flag
        // which can be used later.
        boolean dividendOutOfRange = false;
        if (dividend == Integer.MIN_VALUE) {
            dividend += 1;
            dividendOutOfRange = true;
        }

        // Convert both to positive for easy subtraction
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        // Calculate the quotient
        int quo = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            quo++;
        }

        // Check if dividend was out of range and if adding a 1 gives us a number = divisor, so we can divide one more time and increase the quotient
        if (dividendOutOfRange && ((dividend + 1) == divisor)) {
            quo++;
        }

        return isNeg ? -quo : quo;
    }

}
