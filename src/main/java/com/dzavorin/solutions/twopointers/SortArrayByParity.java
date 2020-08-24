package com.dzavorin.solutions.twopointers;

public class SortArrayByParity {

    public int[] sortArrayByParity(int[] A) {
        int i = 0;
        int j = A.length - 1;

        while (i < j) {
            if (A[i] % 2 == 0) {
                i++;
            } else {
                int t = A[i];
                A[i] = A[j];
                A[j] = t;
                j--;
            }
        }
        return A;

    }

}
