package com.dzavorin.solutions.parsing;

import com.dzavorin.solutions.tree.MaximumWidthOfBinaryTree.Pair;

import java.util.*;
import java.util.function.BiFunction;

public class ParsingABooleanExpression {

    Map<Character, BiFunction<String, Integer, Pair<Boolean, Integer>>> map = new HashMap<>();

    ParsingABooleanExpression() {
        map.put('!', this::parseNot);
        map.put('&', this::parseAnd);
        map.put('|', this::parseOr);
        map.put('t', this::parseValue);
        map.put('f', this::parseValue);
    }

    public boolean parseBoolExpr(String expression) {
        return expression != null && expression.length() != 0 ?
                parse(expression, 0).getKey() : false;
    }

    Pair<Boolean, Integer> parseValue(String expression, int i) {
        return new Pair<>(expression.charAt(i) == 't', i);
    }

    Pair<Boolean, Integer> parse(String expression, int i) {
        return map.get(expression.charAt(i)).apply(expression, i);
    }

    Pair<Boolean, Integer> parseOr(String expression, int i) {
        Pair<List<Boolean>, Integer> pair = parseBracket(expression,i + 2);
        Boolean eval = pair.getKey()
                .stream()
                .reduce((a, b) -> a | b)
                .get();
        return new Pair<>(eval, pair.getValue());
    }

    Pair<Boolean, Integer> parseAnd(String expression, int i) {
        Pair<List<Boolean>, Integer> pair = parseBracket(expression,i + 2);
        Boolean eval = pair.getKey()
                .stream()
                .reduce((a, b) -> a & b)
                .get();
        return new Pair<>(eval, pair.getValue());
    }

    Pair<Boolean, Integer> parseNot(String expression, int i) {
        Pair<List<Boolean>, Integer> pair = parseBracket(expression,i + 2);
        return new Pair<>(!pair.getKey().get(0), pair.getValue());
    }

    Pair<List<Boolean>, Integer> parseBracket(String expression, int i) {
        int j = i;
        List<Boolean> res = new ArrayList<>();
        while (j < expression.length()) {
            char ch = expression.charAt(j);
            if (ch == ')') {
                j++;
                break;
            } else if (ch == ',') {
                Pair<Boolean, Integer> pair = parse(expression, j + 1);
                res.add(pair.getKey());
                j = pair.getValue();
            } else {
                Pair<Boolean, Integer> pair = parse(expression, j);
                res.add(pair.getKey());
                j = pair.getValue();
            }
            j++;
        }

        return new Pair<>(res, j);
    }

    //////////

    public boolean parseBoolExpr2(String expression) {
        if (expression == null || expression.length() == 0) return false;

        Stack<Character> ops = new Stack<>();
        Stack<Boolean> operands = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == 'f')
                operands.push(false);
            else if (ch == 't')
                operands.push(true);
            else if (ch == '(')
                operands.push(null);
            else if (ch == ')') {
                char operator = ops.pop();

                boolean res = true;
                if (operator == '|')
                    res = false;

                while (!operands.isEmpty() && operands.peek() != null) {
                    if (operator == '|')
                        res |= operands.pop();
                    else if (operator == '&')
                        res &= operands.pop();
                    else if (operator == '!')
                        res = !operands.pop();
                }
                operands.pop();
                operands.push(res);

            } else if (ch == ',')
                continue;
            else {
                ops.push(ch);
            }
        }
        return operands.peek();
    }


    public static void main(String[] args) {
        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("!(t)")); // false

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("t")); // true

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("|(f,t)")); // true

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("|(&(t,f,t),!(t))")); // false

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("!(&(&(!(&(f)),&(t),|(f,f,t)),&(t),&(t,t,f)))")); // true

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("!(&(f))")); // true

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("&(!(&(f)),&(t),|(f,f,t))")); // true

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("&(t,t,f)")); // false

        System.out.println(new ParsingABooleanExpression()
                .parseBoolExpr("&(&(!(&(f)),&(t),|(f,f,t)),&(t),&(t,t,f))")); // false
    }

}
