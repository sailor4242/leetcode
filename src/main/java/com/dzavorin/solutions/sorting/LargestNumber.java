package com.dzavorin.solutions.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LargestNumber {

    public String largestNumber(int[] nums) {

        Map<Integer, String> map = new HashMap<>();

        for (int n : nums) {
            map.put(n, String.valueOf(n));
        }

        Comparator<Integer> comp = (n1, n2) -> {
            String s1 = map.get(n1);
            String s2 = map.get(n2);

            if (s1.length() > s2.length()) {
                int nn1 = Integer.parseInt(s1.substring(0, s2.length()));
                if (nn1 > n2) {
                    return -1;
                } else if (n2 > nn1) {
                    return 1;
                } else {
                    return -comp(s1, s2);
                }
            } else if (s2.length() > s1.length()) {
                int nn2 = Integer.parseInt(s2.substring(0, s1.length()));
                if (nn2 > n1) {
                    return 1;
                } else if (n1 > nn2) {
                    return -1;
                } else {
                    return comp(s2, s1);
                }
            } else {
                return Integer.compare(n2, n1);
            }
        };

        StringBuilder sb = new StringBuilder();
        Arrays.stream(nums).boxed()
                .sorted(comp)
                .forEach(n -> {
                    sb.append(map.get(n));
                });

        while (sb.length() > 1) {
            if (sb.charAt(0) == '0') {
                sb.deleteCharAt(0);
            } else {
                break;
            }
        }

        return sb.toString();
    }

    public String largestNumber2(int[] nums) {
        String result = Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((a, b) -> (b + a).compareTo(a + b))
                .reduce((a, b) -> a + b)
                .get();

        return result.startsWith("0") ? "0" : result; // check if result have all 0's then just return "0" else result
    }

    private int comp(String s1, String s2) {
        int j = 0;
        int i = s2.length();
        while (i < s1.length()) {
            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(j);
            if (ch1 > ch2) {
                return 1;
            } else if (ch2 > ch1) {
                return -1;
            } else {
                j++;
                if (j >= s2.length()) {
                    j = 0;
                    i++;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new LargestNumber().largestNumber(new int[]{3, 30, 34, 5, 9})); // "9534330"
        System.out.println(new LargestNumber().largestNumber(new int[]{10, 2})); // "210"
        System.out.println(new LargestNumber().largestNumber(new int[]{2, 10})); // "210"
        System.out.println(new LargestNumber().largestNumber(new int[]{121,12})); // "12121"
        System.out.println(new LargestNumber().largestNumber(new int[]{128,12})); // "12821"
        System.out.println(new LargestNumber().largestNumber(new int[]{128,0,0,1})); // "128100"
        System.out.println(new LargestNumber().largestNumber(new int[]{0,0})); // "0"
        System.out.println(new LargestNumber().largestNumber(new int[]{0,0,0,0})); // "0"
    }
}
