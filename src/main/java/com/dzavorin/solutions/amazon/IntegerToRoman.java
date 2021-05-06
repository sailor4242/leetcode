package com.dzavorin.solutions.amazon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class IntegerToRoman {

    public String intToRoman(int num) {

        Map<Integer, String> map1 = Map.of(
                1, "I",
                4, "IV",
                5, "V",
                9,  "IX",
                10, "X",
                40, "XL");

        Map<Integer, String> map2 = Map.of(50, "L",
                90, "XC",
                100, "C",
                400, "CD",
                500, "D",
                900, "CM",
                1000, "M");

        Map<Integer, String> map = new HashMap<>();
        map.putAll(map1);
        map.putAll(map2);

        LinkedList<Integer> parts = new LinkedList<>();
        int j = 10;
        while (num > 0) {
            parts.addFirst(num % j);
            System.out.println(num % j);
            num /= j;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.size(); i++) {

            int pow = parts.size() - i - 1;
            int decPow = (int) Math.pow(10, pow);
            int n = parts.get(i);

            String s = map.get(n * decPow);
            if (s != null) {
                sb.append(s);
            } else {

                int mod = n - 5;
                if (mod > 0) {
                    sb.append(map.get(5 * decPow));
                } else {
                    mod = n;
                }

                while (mod > 0) {
                    sb.append(map.get(decPow));
                    mod--;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new IntegerToRoman().intToRoman(3));
        System.out.println(new IntegerToRoman().intToRoman(58));
        System.out.println(new IntegerToRoman().intToRoman(1994));
    }

}
