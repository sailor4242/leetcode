package com.dzavorin.solutions.math;

public class ExcelSheetColumnNumber {

    public int titleToNumber(String s) {
        char a = 'A';

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int j = s.length() - 1 - i;
            sum += Math.pow(26, i) * (s.charAt(j) - a + 1);

        }

        return sum;
    }

}
