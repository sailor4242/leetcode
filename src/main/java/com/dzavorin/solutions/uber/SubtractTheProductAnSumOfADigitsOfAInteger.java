package com.dzavorin.solutions.uber;

public class SubtractTheProductAnSumOfADigitsOfAInteger {

    public int subtractProductAndSum(int n) {
        String num = String.valueOf(n);
        int product = 1;
        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';
            product *= digit;
            sum += digit;
        }

        return product - sum;
    }

}
