package com.dzavorin.solutions;

public class FindNumbersWithEvenNumberOfDigits {

    public int findNumbers(int[] nums) {
        int count = 0;
        for (int n : nums) {
            if (("" + n).length() % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    //////

    public int findNumbers2(int[] nums) {
        int count = 0;
        for (int num: nums) {
            if (checkEven(num)){
                count++;
            }
        }
        return count;
    }
    private boolean checkEven(int num) {
        while (num >= 100) {
            num /= 100;
        }
        if (num > 9) {
            return true;
        }
        return false;
    }
}
