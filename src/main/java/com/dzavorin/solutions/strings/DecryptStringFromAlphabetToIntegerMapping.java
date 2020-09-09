package com.dzavorin.solutions.strings;

public class DecryptStringFromAlphabetToIntegerMapping {

    public String freqAlphabets(String s) {
        int i = s.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0) {
            if (s.charAt(i) == '#') {
                sb.append((char) (Integer.parseInt(s.substring(i - 2, i)) + 'a' - 1));
                i = i - 3;
            } else {
                sb.append((char) (Integer.parseInt(s.substring(i, i + 1)) + 'a' - 1));
                i = i - 1;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new DecryptStringFromAlphabetToIntegerMapping().freqAlphabets("10#11#12"));
        System.out.println(new DecryptStringFromAlphabetToIntegerMapping().freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"));
    }
}
