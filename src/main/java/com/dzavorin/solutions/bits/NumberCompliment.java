package com.dzavorin.solutions.bits;

public class NumberCompliment {

    public int findComplement(int num) {
        return num ^ ((1 << Integer.toBinaryString(num).length()) -1);
    }

    public int findComplement2(int num) {
        // The function highestOneBit returns an int value with at most a single one-bit,
        // in the position of the highest-order ("leftmost") one-bit in the specified int value.
        // Returns zero if the specified value is equal to zero.
        //
        // Note that any bit xor "1" gives the complement of that bit.
        // To find the complement of "num",
        // we generate a number that has the same number of digits as "num" but all of its bit are set to "1",
        // and then return the generated number xor "num"
        //
        // Example: num = 10 (0b1010)
        // highestOneBit = 8 (0b1000)
        // Making a shift left of the highestOneBit = 16 (0b10000)
        // Subtracting "1" from 16 = 15 (0b1111) which is a number that has the same number of digits as "num"
        // but all of its bit are set to "1".
        // Make xor between the generated number and "num" = 15 xor 10 = 0b1111 xor 0b1010 = 0b0101 (5)

        int highestOneBit = Integer.highestOneBit(num);
        return ((highestOneBit << 1) - 1) ^ num;
    }


}
