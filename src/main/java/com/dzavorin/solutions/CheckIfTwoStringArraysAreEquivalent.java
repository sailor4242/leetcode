package com.dzavorin.solutions;

public class CheckIfTwoStringArraysAreEquivalent {


    // two pointers, constant memory
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        int r = 0;
        int j = 0;

        for (int i = 0; i < word1.length; i++) {
            String s1 = word1[i];
            for (int l = 0; l < s1.length(); l++) {

                char ch1 = s1.charAt(l);

                if (r >= word2[j].length()) {
                    r = 0;
                    j++;
                }

                if (j >= word2.length) {
                    return false;
                }

                char ch2 = word2[j].charAt(r);

                if (ch1 != ch2) {
                    return false;
                }

                r++;
            }
        }

        return j == word2.length - 1 && r == word2[j].length();
    }

    // O(n + m) memory

    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String wd : word1) {
            sb1.append(wd);
        }
        for (String wd : word2) {
            sb2.append(wd);
        }
        return sb1.toString().equals(sb2.toString());
    }

}
