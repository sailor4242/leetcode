package com.dzavorin.solutions.greedy;

public class MinimumDominoRotationForEqualRow {

    public int minDominoRotations(int[] A, int[] B) {
        int[] countsA = new int[7];
        int[] countsB = new int[7];
        int[] countsC = new int[7];

        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[i]) {
                countsC[A[i]]++;
            } else {
                countsA[A[i]]++;
                countsB[B[i]]++;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < countsA.length; i++) {
            if (countsA[i] + countsB[i] + countsC[i] >= A.length) {
                min = Math.min(min, A.length - countsC[i] - Math.max(countsA[i], countsB[i]));
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumDominoRotationForEqualRow()
                .minDominoRotations(new int[]{2,1,2,4,2,2}, new int[]{5,2,6,2,3,2}));

        System.out.println(new MinimumDominoRotationForEqualRow()
                .minDominoRotations(new int[]{1,1,1,1}, new int[]{1,1,1,1}));
    }
}
