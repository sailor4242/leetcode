package com.dzavorin.solutions.strings;

import java.util.ArrayList;
import java.util.List;

public class CheckIfOneStringSwapCanMakeStringsEqual {

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        char[] arr1 = new char[26];
        char[] arr2 = new char[26];

        for (int i = 0; i < s1.length(); i++) {
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) return false;
        }

        List<Character> list = new ArrayList<>();

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                list.add(s1.charAt(i));
                list.add(s2.charAt(i));
            }
        }

        return list.size() == 4 && list.get(0) == list.get(3) && list.get(1) == list.get(2);

    }

}
