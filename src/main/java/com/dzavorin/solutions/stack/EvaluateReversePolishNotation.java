package com.dzavorin.solutions.stack;

import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiFunction;

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {

        LinkedList<Integer> digits = new LinkedList<>();

        Map<String, BiFunction<Integer,Integer,Integer>> map = Map.of(
                "+", (a, b) -> a + b,
                "*", (a, b) -> a * b,
                "/", (a, b) -> a / b,
                "-", (a, b) -> a - b
        );

        for (String token : tokens) {
            Integer digit = digit(token);
            if (digit != null) {
                digits.add(digit);
            } else {
                Integer one = digits.removeLast();
                Integer two = digits.removeLast();

                digits.add(map.get(token).apply(two, one));
            }
        }

        return digits.removeLast();
    }

    private Integer digit(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return null;
        }
    }

}
