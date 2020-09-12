package com.dzavorin.solutions.twopointers;

public class CompareVersionPointers {

    public int compareVersion(String version1, String version2) {
        String[] vOne = version1.split("\\.");
        String[] vTwo = version2.split("\\.");

        int i = 0;
        int j = 0;

        while (i < vOne.length && j < vTwo.length) {

            Integer sOne = Integer.parseInt(vOne[i]);
            Integer sTwo = Integer.parseInt(vTwo[i]);

            if (sOne < sTwo) {
                return -1;
            } else if (sOne > sTwo) {
                return 1;
            }

            i++;
            j++;

        }

        if (i < vOne.length) {
            while (i < vOne.length) {
                Integer n = Integer.parseInt(vOne[i]);
                if (n != 0) {
                    return 1;
                }
                i++;
            }
        } else if (j < vTwo.length) {
            while (j < vTwo.length) {
                Integer n = Integer.parseInt(vTwo[j]);
                if (n != 0) {
                    return -1;
                }
                j++;
            }
        }

        return 0;

    }

    public static void main(String[] args) {
//        System.out.println(new CompareVersionPointers().compareVersion("0.1", "1.1"));
        System.out.println(new CompareVersionPointers().compareVersion("1.0.1", "1"));
    }
}
