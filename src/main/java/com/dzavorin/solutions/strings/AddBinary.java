package com.dzavorin.solutions.strings;

public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        String tmp;
        if (a.length() > b.length()) {
            tmp = a;
            a = b;
            b = tmp;
        }

        boolean next = false;
        for (int i = 0; i < a.length(); i++) {
            String cur = "0";

            char cha = a.charAt(a.length() - i - 1);
            char chb = b.charAt(b.length() - i - 1);

            if (cha == '1' && chb == '1') {
                if (next) {
                    cur = "1";
                } else {
                    next = true;
                }
            } else if ((cha == '0' && chb == '1')
                    || (cha == '1' && chb == '0')) {

                if (!next) {
                    cur = "1";
                }
            } else {
                if (next) {
                    cur = "1";
                    next = false;
                }
            }

            sb.append(cur);
        }

        for (int i = b.length() - a.length() - 1; i >= 0; i--) {

            if (b.charAt(i) == '1') {
                if (next) {
                    sb.append("0");
                } else {
                    sb.append("1");
                }

            } else {
                if (next) {
                    sb.append("1");
                    next = false;
                } else {
                    sb.append("0");
                }
            }

        }

        if (next) {
            sb.append('1');
        }

        return sb.reverse().toString();
    }

}
