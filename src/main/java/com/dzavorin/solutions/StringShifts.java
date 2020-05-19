package com.dzavorin.solutions;

class StringShifts {

    int m, l, r = 0;

    public static String stringShift(String s, int[][] shift) {
        for (int i = 0; i < shift.length; i++) {
            int[] ss = shift[i];
            if (ss[0] == 0) {
                String p = s.substring(0, ss[1]);
                String r = s.substring(ss[1]);
                s = r + p;
            } else if (ss[0] == 1) {
                String p = s.substring(s.length() - ss[1]);
                String r = s.substring(0, s.length() - ss[1]);
                s = p + r;
            }
        }

        return s;
    }

    public static String reverse(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length / 2; i++) {
            char cc = c[i];
            c[i] = c[c.length - i - 1];
            c[c.length - i - 1] = cc;
        }
        return new String(c);
    }

    public static void main(String[] args) {
//        System.out.println(stringShift("abc", new int[][] {{0, 1}, {1, 2}}));
        System.out.println(stringShift("abcdefg", new int[][] {{1, 1}, {1, 1}, {0, 2}, {1, 3}}));
    }
}
