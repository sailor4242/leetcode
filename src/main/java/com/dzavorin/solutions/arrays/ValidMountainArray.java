package com.dzavorin.solutions.arrays;

public class ValidMountainArray {

    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) return false;

        boolean summit = false;
        for (int i = 2; i < arr.length; i++) {
            int prevPrev = arr[i - 2];
            int prev = arr[i - 1];
            int cur = arr[i];

            if (prevPrev == prev || prev == cur) {
                return false;
            } else if ((prevPrev > prev && cur > prev) || (summit && cur > prev)) {
                return false;
            } else if (prevPrev < prev && prev > cur) {
                summit = true;
            }
        }

        return summit;
    }

    //////

    public boolean validMountainArray2(int[] arr) {
        int N = arr.length;
        int i = 0;

        while (i < N-1 && arr[i] < arr[i+1]){
            i++;
        }
        // peak can not be head and tail
        if(i == 0 || i == N-1){
            return false;
        }
        while (i < N-1 && arr[i] > arr[i+1]){
            i++;
        }
        return i == N-1;
    }


}
