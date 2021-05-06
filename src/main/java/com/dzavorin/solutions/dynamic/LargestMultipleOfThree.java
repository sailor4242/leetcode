package com.dzavorin.solutions.dynamic;

import java.util.Arrays;

public class LargestMultipleOfThree {

    public String largestMultipleOfThree(int[] digits) {
        digits = Arrays.stream(digits).boxed().sorted((a, b) -> (b - a)).mapToInt(i -> i).toArray();
        String[] strs = new String[3];
        Arrays.fill(strs, "");

        for (int digit : digits) {
            String[] strsCopy = Arrays.stream(strs).toArray(String[]::new);
            int mod = digit % 3;
            for (int j = 0; j < 3; j++) {
                String prevStr = strsCopy[j];
                if (prevStr.length() == 0 && j == mod) {
                    strs[mod] = getMax(String.valueOf(digit), strs[mod]);
                } else if (prevStr.length() > 0) {
                    strs[(j + mod) % 3] = getMax(strsCopy[(j + mod) % 3], prevStr + digit);
                }
            }
        }
        return strs[0].startsWith("0") ? "0" : strs[0];
    }

    private String getMax(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return str1;
        } else if (str2.length() > str1.length()) {
            return str2;
        }
        return str1.compareTo(str2) > 0 ? str1 : str2;
    }

    public static void main(String[] args) {
        System.out.println(new LargestMultipleOfThree().largestMultipleOfThree(new int[]{8, 1, 9}));

        for (int i = 0; i < 10; i++) {
            System.out.println(i + " - " + i % 3);
        }
    }

}
