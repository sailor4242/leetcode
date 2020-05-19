package com.dzavorin.solutions;

public class FirstBadVersion {

    public int firstBadVersion(int n) {
        int lo = 1;
        int hi = n;
        int l = n;
        while (lo <= hi) {
            int m = lo + (hi - lo) / 2;
            boolean isb = isBadVersion(m);
            if (isb) {
                l = Math.min(l, m);
                hi = m - 1;
            } else {
                lo = m + 1;
            }
        }

        return l;
    }

    // some leetcode task api mock
    private boolean isBadVersion(int m) {
        return false;
    }

}
