package com.dzavorin.solutions.strings;

public class DetectCapital {

    public boolean detectCapitalUse(String word) {

        if (word.length() == 0) {
            return false;
        }

        boolean isFirstCapital = isCapital(word.charAt(0));

        int capitalCount = 0;
        for (int i = 1; i < word.length(); i++) {

            if (isCapital(word.charAt(i))) {
                capitalCount++;
            }
        }


        return capitalCount == 0 || (isFirstCapital && capitalCount + 1 == word.length());

    }

    public boolean isCapital(char x) {
        char a = 'A';
        char z = 'Z';
        return x >= a && x <= z;
    }

}
