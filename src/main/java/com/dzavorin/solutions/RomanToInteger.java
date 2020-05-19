package com.dzavorin.solutions;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    Map<Character, Integer> map = new HashMap<>();

    public int romanToInt(String s) {

        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        if (s.length() == 1) {
            return map.get(s.charAt(0));
        }

        int ss = 0;

        for(int i = 0; i < s.length() - 1; i++) {
            Integer n = map.get(s.charAt(i));
            Integer m = map.get(s.charAt(i + 1));

            if (m > n) {
                ss += m - n;
                i++;
                if (i + 1 == s.length() - 1) {
                    ss += map.get(s.charAt(i + 1));
                }

            } else {
                ss +=n;
                if (i + 1 == s.length() - 1) {
                    ss+=m;
                }
            }

        }

        return ss;
    }


    public static void main(String[] args) {
//        System.out.println(new RomanToInteger().romanToInt("III")); //3
//        System.out.println(new RomanToInteger().romanToInt("IV")); //4
//        System.out.println(new RomanToInteger().romanToInt("IX")); //9
//        System.out.println(new RomanToInteger().romanToInt("LVIII")); //58
//        System.out.println(new RomanToInteger().romanToInt("MCMXCIV")); //1994
        System.out.println(new RomanToInteger().romanToInt("MDCXCV")); //1695
    }
}
