package com.dzavorin.solutions.bits;

public class ReverseBits {

    public int reverseBits(int n) {
        return Integer.parseUnsignedInt(
                new StringBuilder(String.format("%32s", Integer.toBinaryString(n)).replace(' ', '0'))
                        .reverse()
                        .toString(), 2
        );
    }

}
