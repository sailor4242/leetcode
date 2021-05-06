package com.dzavorin.solutions.amazon;

import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class DesignAnExpressionTreeWithEvaluateFunction {

    abstract class Node {
        public abstract int evaluate();
        // define your fields here
    };

    class Nod extends Node {
        Nod left;
        Nod right;
        String val;

        Nod(String val) {
            this.val = val;
        }

        @Override
        public int evaluate() {

            int l = left != null ? left.evaluate() : 0;
            int r = right != null ? right.evaluate() : 0;

            if ("+".equals(val)) {
                return l + r;
            } else if ("-".equals(val)) {
                return l - r;
            } else if ("*".equals(val)) {
                return l * r;
            } else if ("/".equals(val)) {
                return l / r;
            } else {
                return Integer.parseInt(val);
            }
        }
    }


    /**
     * This is the TreeBuilder class.
     * You can treat it as the driver code that takes the postinfix input
     * and returns the expression tree represnting it as a Node.
     */

    class TreeBuilder {
        Node buildTree(String[] postfix) {

            LinkedList<Nod> ints = new LinkedList<>();
            LinkedList<Nod> ops = new LinkedList<>();
            boolean beReady = false;
            for (int i = 0; i < postfix.length; i++) {
                String str = postfix[i];
                char ch = str.charAt(0);
                if (Character.isDigit(ch)) {

                    if (beReady) {
                        while (!ops.isEmpty()) {
                            Nod nod = ops.removeFirst();
                            nod.right = ints.removeLast();
                            nod.left = ints.removeLast();
                            ints.add(nod);
                        }
                        beReady = false;
                    }

                    ints.add(new Nod(str));
                } else {
                    ops.add(new Nod(str));
                    beReady = true;
                }

            }

            if (beReady) {
                while (!ops.isEmpty()) {
                    Nod nod = ops.removeFirst();
                    nod.right = ints.removeLast();
                    nod.left = ints.removeLast();
                    ints.add(nod);
                }

            }

            Nod nod = ints.removeLast();
            System.out.println(nod.val);
            return nod;

        }

    };

    ///////

    final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS =
            Map.ofEntries(
                    Map.entry("+", (op1, op2) -> op1 + op2),
                    Map.entry("-", (op1, op2) -> op1 - op2),
                    Map.entry("*", (op1, op2) -> op1 * op2),
                    Map.entry("/", (op1, op2) -> op1 / op2)
            );

    class Node2 {

        Node2(String val, Node2 left, Node2 right){
            this.val = val;
            this.left = left;
            this.right = right;
        }

        final Node2 left;
        final Node2 right;
        final String val;

        public int evaluate(){
            if(OPERATIONS.containsKey(val)){
                return OPERATIONS.get(val).apply(left.evaluate(), right.evaluate());
            }
            return Integer.valueOf(val);
        }
    };

    class TreeBuilder2 {
        Node2 buildTree(String[] postfix) {
            Stack<Node2> stack = new Stack<>();
            for(String token: postfix){
                if(OPERATIONS.keySet().contains(token)){
                    // operator
                    Node2 o2 = stack.pop();
                    Node2 o1 = stack.pop();
                    stack.push(new Node2(token, o1, o2));
                } else{
                    // operand
                    stack.push(new Node2(token, null, null));
                }
            }
            return stack.pop();
        }
    };
}
