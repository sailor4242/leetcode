package com.dzavorin.solutions;

public class UniqueSubstringsInAWraparoundString {

    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int max = 1;
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) - p.charAt(i - 1) == 1 || p.charAt(i - 1) - p.charAt(i) == 25) {
                max++;
            } else {
                max = 1;
            }

            int curCh = p.charAt(i) - 'a';
            count[curCh] = Math.max(count[curCh], max);
        }

        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += count[i];
        }

        return sum;
    }

}
