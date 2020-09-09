package com.dzavorin.solutions.strings;

import java.util.HashSet;
import java.util.Set;

public class RepeatedStringMatch {

    public int repeatedStringMatch(String a, String b) {
        Set<Character> as = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            as.add(a.charAt(i));
        }

        Set<Character> bs = new HashSet<>();
        for (int i = 0; i < b.length(); i++) {
            bs.add(b.charAt(i));
        }

        for (Character bc : bs) {
            if (!as.contains(bc)) return -1;
        }

        String c = a;
        int count = 1;
        while (!check(c, b)) {
            if (c.length() > b.length() && count > 3) return -1;
            c += a;
            count++;
        }
        return count;
    }

    // KMP algo - O(n + m)
    boolean check(String c, String b) {
        if (c.length() < b.length()) return false;

        int[] kmp = new int[b.length()];
        int i = 1;
        int j = 0;
        kmp[j] = 0;
        while (i < b.length()) {

            if (b.charAt(i) != b.charAt(j)) {
                if (j != 0) {
                    j = kmp[j - 1];
                } else {
                    kmp[i] = 0;
                    i++;
                }
            } else {
                kmp[i] = j + 1;
                i++;
                j++;

            }
        }

        i = 0;
        j = 0;
        while (i < c.length() && j < b.length()) {
            if (c.charAt(i) != b.charAt(j)) {
                if (j != 0) {
                    j = kmp[j - 1];
                } else {
                    i++;
                }
            } else {
                i++;
                j++;
            }
        }
        return j == b.length();
    }

    // Rabin-Karp
    public int repeatedStringMatch2(String A, String B) {
        if (A == null || B == null || A.length() == 0 || B.length() == 0)
            return -1;
        if (A.contains(B))
            return 1;

        int times = 1;

        // Prime number to calculate hash
        int x = 53;

        // find B's hash
        int bHash = hashCode(B, x);

        // Make a string by appending A.
        // candidate string length will be same B
        StringBuilder candidate = new StringBuilder(A);
        while (candidate.length() < B.length()) {
            candidate.append(A);
            times++;
        }
        // window to calcualte hash and comapre string
        int windowSize = B.length();

        // hash for first windowSize characters of candidate
        int cHash = hashCode(candidate.substring(0, windowSize), x);
        if (bHash == cHash && candidate.substring(0, windowSize).equals(B))
            return times;

        // Max power use for updating hash
        int xPow = 1;
        for (int i = 0; i < B.length() - 1; i++)
            xPow *= x;

        // udpate the hash for characters in the window
        int i = windowSize;
        for (; i < candidate.length(); i++) {
            int toRemove = candidate.charAt(i - windowSize);
            cHash = (cHash - toRemove * xPow) * x + candidate.charAt(i);
            // comapre hash first then content of string
            if (bHash == cHash && candidate.substring(i - windowSize + 1, i + 1).equals(B))
                return times;
        }
        // Append one more time by making candidate string > B
        candidate.append(A);

        // Comapare hash from previous window end i to end of new length
        for (; i < candidate.length(); i++) {
            int toRemove = candidate.charAt(i - windowSize);
            cHash = (cHash - toRemove * xPow) * x + candidate.charAt(i);
            if (bHash == cHash && candidate.substring(i - windowSize + 1, i + 1).equals(B))
                // candidate was appended by A above
                return times + 1;
        }

        return -1;
    }

    // Util fucntion to calcualte hash of given string
    private int hashCode(String str, int x) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * x + str.charAt(i);
        }
        return hash;
    }

    public static void main(String[] args) {
        System.out.println(new RepeatedStringMatch().repeatedStringMatch("abcd", "cdabcdab"));
        System.out.println(new RepeatedStringMatch().repeatedStringMatch("abcd", "xw"));
        System.out.println(new RepeatedStringMatch().check("abcdabcdabcd", "cdabcdab"));
    }
}
