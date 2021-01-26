package com.dzavorin.solutions.dynamic;

public class MinimumSwapsToMakeSequenceIncreasing {

    public int minSwap(int[] A, int[] B) {
        // n: natural, s: swapped
        int n1 = 0;
        int s1 = 1;
        for (int i = 1; i < A.length; i++) {
            int n2 = Integer.MAX_VALUE;
            int s2 = Integer.MAX_VALUE;
            if (A[i - 1] < A[i] && B[i - 1] < B[i]) {
                n2 = Math.min(n2, n1);
                s2 = Math.min(s2, s1 + 1);
            }
            if (A[i - 1] < B[i] && B[i - 1] < A[i]) {
                n2 = Math.min(n2, s1);
                s2 = Math.min(s2, n1 + 1);
            }
            n1 = n2;
            s1 = s2;
        }
        return Math.min(n1, s1);
    }

    public int minSwap2(int[] A, int[] B) {
        int ans = 0;
        int count = 0;
        int swaps = 0;
        for (int i = A.length - 2; i >= 0; i--) {
            if (Math.max(A[i], B[i]) >= Math.min(A[i + 1], B[i + 1])) {
                count++;
                if (A[i] >= A[i + 1] || B[i] >= B[i + 1]) {
                    int temp = A[i];
                    A[i] = B[i];
                    B[i] = temp;
                    swaps++;
                }
            } else {
                ans += Math.min(swaps, 1 + count - swaps);
                count = 0;
                swaps = 0;
            }
        }
        ans += Math.min(swaps, 1 + count - swaps);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumSwapsToMakeSequenceIncreasing().minSwap(new int[]{3, 3, 8, 9, 10}, new int[]{1, 7, 4, 6, 8}));
    }
}
