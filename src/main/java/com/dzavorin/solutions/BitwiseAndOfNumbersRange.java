package com.dzavorin.solutions;

public class BitwiseAndOfNumbersRange {

    public int rangeBitwiseAnd(int m, int n) {
        return m & n;
    }

    public static void main(String[] args) {
        System.out.println(new BitwiseAndOfNumbersRange().rangeBitwiseAnd(10,1));
    }
}
