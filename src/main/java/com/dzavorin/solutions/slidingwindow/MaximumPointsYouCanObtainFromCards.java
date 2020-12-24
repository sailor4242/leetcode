package com.dzavorin.solutions.slidingwindow;

public class MaximumPointsYouCanObtainFromCards {

    public int maxScore(int[] cardPoints, int k) {

        int s = 0;
        int hi = cardPoints.length - k - 1;
        int sw = 0;
        for (int i = 0; i < cardPoints.length; i++) {
            s += cardPoints[i];
            if (i == hi) {
                sw = s;
            }
        }

        if (k >= cardPoints.length) return s;
        int res = s - sw;
        int lo = 0;
        hi++;
        while (hi < cardPoints.length) {
            sw = sw - cardPoints[lo++] + cardPoints[hi++];
            res = Math.max(res, s - sw);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{1,2,3,4,5,6,1}, 3));
        System.out.println(new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{9,7,7,9,7,7,9}, 7));
        System.out.println(new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{9,7,7,9,7,7,9}, 6));
        System.out.println(new MaximumPointsYouCanObtainFromCards().maxScore(new int[]{1,79,80,1,1, 1,200,1}, 3)); //202
    }
}
