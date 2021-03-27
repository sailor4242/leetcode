package com.dzavorin.solutions.arrays;

public class KthMissingPositiveInteger {

    public int findKthPositive(int[] arr, int k) {
        int j = 0;
        int len = arr[arr.length - 1] + k;
        for (int i = 1; i <= len; i++) {
            if (j < arr.length && arr[j] == i) {
                j++;
            } else {
                k--;
            }

            if (k == 0) {
                return i;
            }
        }
        return -1;
    }

    public int findKthPositive2(int[] arr, int k) {
        int prev = 0;
        for (int n : arr) {
            if (k > (n - prev - 1)) {
                k -= (n - prev - 1);
            } else {
                return prev + k;
            }
            prev = n;
        }

        return arr[arr.length - 1] + k;
    }

    public int findKthPositive3(int[] arr, int k) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] - (mid + 1) >= k) {
                hi = mid;  //missed more or equal than k numbers, left side;
            } else {
                lo = mid + 1;   // missed less than k numbers, must be in the right side;
            }
        }
        return lo + k;
    }

    /////

    public int findKthPositive4(int[] arr, int k) {

        int[] sarr = new int[1001];

        for (int i = 0 ; i < arr.length; i++) {
            sarr[arr[i]]++;
        }

        for (int i = 1; i < sarr.length; i++) {
            if (sarr[i] == 0) {
                k--;
                if (k == 0) return i;
            }
        }

        return 1000 + k;
    }

    public static void main(String[] args) {
        System.out.println(new KthMissingPositiveInteger().findKthPositive(new int[]{1, 3}, 5));
    }
}
