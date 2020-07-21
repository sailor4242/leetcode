package com.dzavorin.solutions.bits;

public class HamiltonDistance {

    public int hammingDistance(int x, int y) {
        String s1 = Integer.toBinaryString(x);
        String s2 = Integer.toBinaryString(y);
        int dist = 0;
        int i = 0;

        s1 = String.format("%32s", s1).replace(' ', '0');
        s2 = String.format("%32s", s2).replace(' ', '0');
        while (i < s1.length()) {
            if (s1.charAt(i) != s2.charAt(i)) {
                dist++;
            }
            i++;
        }
        return dist;
    }

    public int hammingDistance2(int x, int y) {
        int xor = x^y;
        int dis = 0;
        while(xor > 0){
            if((xor & 1) == 1) dis++;
            xor>>=1;
        }
        return dis;
    }
}
