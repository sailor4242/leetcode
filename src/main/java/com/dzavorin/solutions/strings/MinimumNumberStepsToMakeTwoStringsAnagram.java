package com.dzavorin.solutions.strings;

public class MinimumNumberStepsToMakeTwoStringsAnagram {

    public int minSteps(String s, String t) {

        char[] sch = new char[26];
        for (int i = 0; i < s.length(); i++) {
            sch[s.charAt(i) - 'a']++;
        }

        int res = 0;
        for (int i = 0; i < t.length(); i++) {
            if (sch[t.charAt(i) - 'a'] > 0) {
                sch[t.charAt(i) - 'a']--;
            } else {
                res++;
            }
        }

        return res;
    }

}
