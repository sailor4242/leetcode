package com.dzavorin.solutions;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        par(0, 0, "", n);
        return res;
    }

    public void par(int left, int right, String s, int n) {
        if (s.length() == n * 2) {
            res.add(s);
            return;
        }
        if (left < n) {
            par(left + 1, right, s + "(", n);
        }
        if (right < left) {
            par(left, right + 1, s + ")", n);
        }

    }

    public static void main(String[] args) {
        System.out.println(new GenerateParenthesis().generateParenthesis(3));
    }
}
