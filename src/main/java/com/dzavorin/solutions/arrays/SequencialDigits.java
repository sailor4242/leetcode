package com.dzavorin.solutions.arrays;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree;
import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;

public class SequencialDigits {

    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new LinkedList<>();

        String sl = String.valueOf(low);
        String sh = String.valueOf(high);

        int[] arr = new int[sl.length()];
        int prev = 0;

        for (int i = 0; i < sl.length(); i++) {
            arr[i] = i + 1;
            prev += (i + 1) * Math.pow(10, sl.length() - i - 1);
        }

        if (low <= prev && prev <= high) {
            res.add(prev);
        }

        Pair<Integer, int[]> pair = next(arr, sh.length());
        int next = pair.getKey();

        while (next <= high && next != prev && next != -1) {
            if (low <= next) {
                res.add(next);
            }
            prev = next;
            pair = next(arr, sh.length());
            next = pair.getKey();
            arr = pair.getValue();
        }

        return res;
    }

    Pair<Integer, int[]> next(int[] arr, int highLength) {
        if (arr[arr.length - 1] == 9) {

            if (arr.length != highLength) {
                arr = new int[arr.length + 1];
                int next = 0;
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = i + 1;
                    next += (i + 1) * Math.pow(10, arr.length - i - 1);
                }

                return new Pair<>(next, arr);
            } else {
                return new Pair<>(-1, arr);
            }
        } else {
            int next = 0;
            int p = arr[0];

            for (int i = 0; i < arr.length; i++) {
                p++;
                arr[i] = p;
                next += arr[i] * Math.pow(10, arr.length - i - 1);
            }
            return new Pair<>(next, arr);

        }
    }


    public List<Integer> sequentialDigits2(int low, int high) {
        List<Integer> res = new ArrayList<>();

        for (int digit = 1; digit < 9; digit++) {
            int next = digit;
            int n = next;

            while (n <= high && next < 10) {
                if (n >= low) {
                    res.add(n);
                }
                int n1 = n * 10 + ++next;
                if (n1 > n) {
                    n = n1;
                } else { // Integer overflow.
                    break;
                }
            }
        }

        Collections.sort(res);
        return res;
    }

    public List<Integer> sequentialDigits3(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String s = "123456789";
        for (int i = 2; i <= s.length(); i++) {
            for (int j = 0; j <= s.length() - i; j++) {
                int num = Integer.parseInt(s.substring(j, j + i));
                if (num > high) return result;
                if (num >= low) result.add(num);
            }
        }

        return result;
    }

    public List<Integer> sequentialDigits4(int low, int high) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < 10; i++) q.add(i); // add 1 - 9 all digits in queue
        while (q.size() > 0) {
            int curr = q.poll(); // poll curr from queue
            if (curr >= low && curr <= high) result.add(curr); // check if current >= low and <= high add
            int lastDigit = curr % 10;
            int next = curr * 10 + lastDigit + 1; // generate next num
            if (lastDigit < 9 && next <= high) q.add(next); // check if next num <= high add in queue
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new SequencialDigits().sequentialDigits(100, 300));
        System.out.println(new SequencialDigits().sequentialDigits(10, 10000));
        System.out.println(new SequencialDigits().sequentialDigits(178546104, 812704742));
    }
}
