package com.dzavorin.solutions.math;

import java.util.Arrays;

public class PlusOne {

    public int[] plusOne(int[] digits) {

        int[] arr = new int[digits.length + 1];
        for (int i = digits.length - 1; i >= 0 ; i--) {
            arr[i + 1] = digits[i];
        }

        for (int i = digits.length - 1; i >= 0 ; i--) {
            if (digits[i] + 1 < 10) {
                arr[i + 1]++;
                break;
            } else {
                arr[i + 1] = 0;
            }
        }

        if (arr[1] == 0) {
            arr[0]++;
        }

        if (arr[0] == 0) {
            return Arrays.copyOfRange(arr, 1, arr.length);
        }

        return arr;
    }
}
