package com.dzavorin.solutions.math;

import java.util.LinkedList;
import java.util.function.Predicate;

public class BasicCalculator2 {

    public int calculate(String s) {

        LinkedList<String> calc = new LinkedList<>();

        Predicate<Character> pred = ch -> ch != '/' && ch != '*' && ch != '+' && ch != '-';

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                continue;
            } else if (pred.test(ch)) {
                int j = i;
                StringBuilder sb = new StringBuilder();

                while (j < s.length() && pred.test(s.charAt(j)) && s.charAt(j) != ' ') {
                    sb.append(s.charAt(j));
                    j++;
                }
                calc.add(sb.toString());
                i = j - 1;
            } else {
                calc.add(String.valueOf(ch));
            }

        }

        LinkedList<String> nextCalc = new LinkedList<>();
        while (!calc.isEmpty()) {
            String str = calc.removeFirst();
            if (str.equals("/")) {
                int prev = Integer.parseInt(nextCalc.removeLast());
                int next = Integer.parseInt(calc.removeFirst());
                nextCalc.add(String.valueOf(prev / next));
            } else if (str.equals("*")) {
                int prev = Integer.parseInt(nextCalc.removeLast());
                int next = Integer.parseInt(calc.removeFirst());
                nextCalc.add(String.valueOf(prev * next));
            } else {
                nextCalc.add(str);
            }

        }

        int res = Integer.parseInt(nextCalc.removeFirst());
        while (!nextCalc.isEmpty()) {
            String str = nextCalc.removeFirst();
            if (str.equals("+")) {
                int next = Integer.parseInt(nextCalc.removeFirst());
                res += next;
            } else if (str.equals("-")) {
                int next = Integer.parseInt(nextCalc.removeFirst());
                res -= next;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculator2().calculate("3 + 5/2"));
        System.out.println(new BasicCalculator2().calculate("3/2 + 5 -3"));
        System.out.println(new BasicCalculator2().calculate("3+2*2"));
        System.out.println(new BasicCalculator2().calculate("3*6/9"));
        System.out.println(new BasicCalculator2().calculate("12-3*4"));
    }

}
