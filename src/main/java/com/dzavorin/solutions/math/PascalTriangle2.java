package com.dzavorin.solutions.math;

import java.util.Arrays;
import java.util.List;

public class PascalTriangle2 {

    public List<Integer> getRow(int rowIndex) {

        Integer[] res = new Integer[rowIndex + 1];

        Arrays.fill(res, 1);

        for (int i = 1; i < rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                res[j] = res[j] + res[j - 1];
            }
        }

        return Arrays.asList(res);
    }

    public static void main(String[] args) {
        System.out.println(new PascalTriangle2().getRow(4));
    }

}
