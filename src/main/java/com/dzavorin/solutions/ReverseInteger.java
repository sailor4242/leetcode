package com.dzavorin.solutions;

public class ReverseInteger {

    public int reverse(int x) {
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(x));
            String s = sb.reverse().toString();
            if (x < 0) {
                return Integer.parseInt("-" + s.substring(0, s.length() - 1));
            } else {
                return Integer.parseInt(s);
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    public int reverse2(int x) {
        long res = 0;
        while (Math.abs(x) > 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return	res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? 0 : (int) res;
    }

}
