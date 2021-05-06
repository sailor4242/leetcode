package com.dzavorin.solutions.amazon;

import java.util.LinkedList;

public class BasicCalculator2 {

    public int calculate(String s) {

        LinkedList<Integer> ints = new LinkedList<>();
        LinkedList<Character> operands = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                sb.append(s.charAt(i));
            }
        }

        s = sb.toString();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (!Character.isDigit(ch)) {
                operands.add(ch);
            } else {
                int j = i + 1;
                for (; j < s.length(); j++) {
                    if (!Character.isDigit(s.charAt(j))) {
                        break;
                    }
                }
                int next = Integer.parseInt(s.substring(i, j));
                i = j - 1;

                if (!operands.isEmpty() && operands.getLast() == '-') {
                    operands.removeLast();
                    operands.add('+');
                    ints.add(-1 * next);
                } else {
                    ints.add(next);
                    while (!operands.isEmpty() && (operands.getLast() == '*' || operands.getLast() == '/')) {
                        char oper = operands.removeLast();
                        int right = ints.removeLast();
                        int left = ints.removeLast();
                        ints.add(oper == '*' ? left * right : left / right);
                    }
                }
            }
        }

        int res = 0;
        while (!ints.isEmpty()) {
            res += ints.removeLast();
        }

        return res;
    }


    ////

    public int calculate2(String s) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
                continue;
            }

            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }

        result += lastNumber;
        return result;
    }
}
