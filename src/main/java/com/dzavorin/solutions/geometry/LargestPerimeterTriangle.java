package com.dzavorin.solutions.geometry;

import java.util.Arrays;

public class LargestPerimeterTriangle {

    public int largestPerimeter2(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; --i) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }
        return 0;
    }

    public int largestPerimeter(int[] A) {
        if (A.length < 2) {
            return 0;
        }

        Arrays.sort(A);
        int res = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = i - 1; j - 1 >= 0; j--) {
                res = Math.max(res, getPerimeter(A[i], A[j], A[j - 1]));
                if (res != 0) {
                    break;
                }
            }
            if (res != 0) {
                break;
            }
        }
        return res;
    }

    public int getPerimeter(int a, int b, int c) {
        if (a + b > c & b + c > a & a + c > b) {
            return a + b + c;
        } else {
            return 0;
        }
    }

}
