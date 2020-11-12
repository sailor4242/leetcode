package com.dzavorin.solutions.strings;

public class BuddyStrings {

    public boolean buddyStrings(String A, String B) {
        if (B.length() != A.length()) return false;

        if (A.equals(B)) {
            int[] count = new int[26];
            for (int i = 0; i < B.length(); i++) {
                count[A.charAt(i) - 'a']++;
                if (count[A.charAt(i) - 'a'] > 1) return true;
            }
            return false;
        }

        for (int i = 0; i < B.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                for (int j = i + 1; j < B.length(); j++) {
                    boolean swaped = false;
                    if (A.charAt(j) == B.charAt(i) && A.charAt(i) == B.charAt(j)) {
                        swaped = true;
                    } else if (A.charAt(j) != B.charAt(j)) {
                        return false;
                    }
                    if (swaped) {
                        for (int k = j + 1; k < B.length(); k++) {
                            if (A.charAt(k) != B.charAt(k)) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

}
