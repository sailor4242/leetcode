package com.dzavorin.solutions.binarysearch;

public class ValidPerfectSquare {

    public boolean isPerfectSquare(int num) {
        int low = 1;
        int high = num;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long mm = mid * mid;
            if (mm == num) {
                return true;
            } else if (mm < num) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
       // System.out.println(new ValidPerfectSquare().isPerfectSquare(9));
        System.out.println(new ValidPerfectSquare().isPerfectSquare(14));
//        System.out.println(new ValidPerfectSquare().isPerfectSquare(16));
    }
}
