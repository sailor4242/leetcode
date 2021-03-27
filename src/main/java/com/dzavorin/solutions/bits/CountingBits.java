package com.dzavorin.solutions.bits;

public class CountingBits {

    public int[] countBits(int num) {
        int[] res = new int[num + 1];

        for (int i = 0; i <= num; i++) {
            int count = 0;
            int j = i;
            System.out.println("---------");
            while (j > 0){
                System.out.println(Integer.toBinaryString(j));
                System.out.println(Integer.toBinaryString(j - 1));
                j = j & (j - 1);
                count++;
                System.out.println(Integer.toBinaryString(j));
                System.out.println("\n");
            }
            res[i] = count;
        }

        return res;
    }


    public static void main(String[] args) {
       new CountingBits().countBits(5);
    }
}
