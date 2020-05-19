package com.dzavorin.solutions;

public class PalindromisSubstrings {

    public int countSubstrings2(String s) {
        int len = s.length(), ans = 0;
        for (int center = 0; center <= 2 * len - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    public int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += pali(s, i, i);
            count += pali(s, i, i + 1);
        }
        return count;
    }

    int pali(String s, int l, int r) {
        int size = 0;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
            size++;
        }
        return size;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromisSubstrings().countSubstrings("aaa"));
    }
}
