package com.dzavorin.solutions.math;

import java.util.Arrays;

public class LargestTimeForGivenDigits {

    public String largestTimeFromDigits(int[] A) {

        Arrays.sort(A);

        StringBuilder sb = new StringBuilder();

        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = A.length - 1; j >= 0; j--) {
                if (i == j) continue;
                String s = "" + A[i] + A[j];
                int n = Integer.parseInt(s);
                int m = 0;
                int[] mins = new int[2];
                if (n < 24) {

                    for (int k = 0; k < A.length; k++) {
                        if (k != i && k != j) {
                            mins[m++] = A[k];
                        }
                    }

                    String minsStr = getMins(mins);
                    if (minsStr != null) {
                        if (s.charAt(0) == '0') {
                            sb.append("0");
                        }
                        sb.append(n);
                        sb.append(":");
                        sb.append(minsStr);
                        return sb.toString();
                    }
                }
            }
        }

        return "";
    }

    private String getMins(int[] mins) {
        String s = null;
        int min = 0;
        int min1 = Integer.parseInt("" + mins[0] + mins[1]);
        int min2 = Integer.parseInt("" + mins[1] + mins[0]);

        if (min1 < 60 && min1 > min2) {
            s = "" + mins[0] + mins[1];
            min = min1;
        } else if (min2 < 60) {
            s = "" + mins[1] + mins[0];
            min = min2;
        } else if (min1 < 60) {
            s = "" + mins[0] + mins[1];
            min = min1;
        }

        if (s == null) return null;

        if (s.charAt(0) == '0') {
            return "0" + min;
        }
        return s;
    }

    public String largestTimeFromDigits2(int[] A) {
        String res = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == j || j == k || i == k) continue;
                    String hour = A[i] + "" + A[j];
                    String min = A[k] + "" + A[6 - i - j - k];
                    String time = hour + ":" + min;
                    if (hour.compareTo("24") < 0 && min.compareTo("60") < 0)
                        res = res.compareTo(time) < 0 ? time : res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(new int[]{1, 2, 3, 4}));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(new int[]{0, 0, 0, 0}));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(new int[]{0, 0, 4, 0}));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(new int[]{1, 9, 6, 0}));
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(new int[]{2, 0, 6, 6}));
    }
}
