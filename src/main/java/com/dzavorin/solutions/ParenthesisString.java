package com.dzavorin.solutions;

import java.util.Stack;

public class ParenthesisString {

    public boolean checkValidString(String s) {
        char[] c = s.toCharArray();
        Stack<Integer> l = new Stack<>();
        Stack<Integer> z = new Stack<>();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(') {
                l.add(i);
            } else if (c[i] == ')') {
                if (!l.isEmpty()) {
                    l.pop();
                } else {
                    if (!z.isEmpty()) {
                        z.pop();
                    } else {
                        return false;
                    }
                }
            } else {
                z.add(i);
            }
        }

        while (!l.isEmpty()) {
            Integer p = l.pop();
            if (!z.isEmpty()) {
                if (p > z.pop()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        System.out.println(new ParenthesisString().checkValidString("((()())((()))(*"));
//        System.out.println(new ParenthesisString().checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*"));
//        System.out.println(new ParenthesisString().checkValidString("(*)"));
//        System.out.println(new ParenthesisString().checkValidString("()"));
//        System.out.println(new ParenthesisString().checkValidString("(((******))"));
//        System.out.println(new ParenthesisString().checkValidString("(*))"));
//        System.out.println(new ParenthesisString().checkValidString("(())(())(((()*()()()))()((()()(*()())))(((*)()"));
//        System.out.println(new ParenthesisString().checkValidString("(())(())(((()*()()()))"));
//        System.out.println(new ParenthesisString().checkValidString("((()()(*()())))(((*)()"));
        System.out.println(new ParenthesisString().checkValidString("((()))()(())(*()()())**(())()()()()((*()*))((*()*)"));
        System.out.println(new ParenthesisString().checkValidString("((()))()(())(*()()())"));
        System.out.println(new ParenthesisString().checkValidString("(*()()())"));

    }
}
